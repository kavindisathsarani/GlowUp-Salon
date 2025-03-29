package lk.ijse.backend.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.backend.dto.SalonServiceDTO;
import lk.ijse.backend.entity.SalonService;
import lk.ijse.backend.enums.ImageType;
import lk.ijse.backend.repo.SalonServiceRepo;
import lk.ijse.backend.service.SalonServicesService;
import lk.ijse.backend.util.ImageUtil;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class SalonServiceImpl implements SalonServicesService {
    private static final Logger logger = LoggerFactory.getLogger(SalonServiceImpl.class);

    @Autowired
    private SalonServiceRepo salonServiceRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ImageUtil imageUtil;

    @Override
    @Transactional
    public SalonServiceDTO<String> save(SalonServiceDTO<String> salonServiceDTO, MultipartFile file) {
        String base64Image = imageUtil.saveImage(ImageType.SALONSERVICE, file);
        logger.info("Base64 image: {}", base64Image);

        SalonService salonService = modelMapper.map(salonServiceDTO, SalonService.class);
        salonService.setImageURL(base64Image); // Assuming image is stored as Base64 in entity

        try {
            SalonService savedService = salonServiceRepo.save(salonService);
            SalonServiceDTO<String> savedDTO = modelMapper.map(savedService, SalonServiceDTO.class);
            savedDTO.setImageURL(base64Image);
            return savedDTO;
        } catch (Exception e) {
            logger.error("Failed to save salon service: {}", salonService, e);
            throw new RuntimeException("Failed to save salon service");
        }
    }

    @Override
    public List<SalonServiceDTO<String>> getAll() {
        List<SalonService> services = salonServiceRepo.findAll();
        List<SalonServiceDTO<String>> serviceDTOs = modelMapper.map(services, new TypeToken<List<SalonServiceDTO<String>>>() {}.getType());
        for (SalonServiceDTO<String> serviceDTO : serviceDTOs) {
            services.stream()
                    .filter(service -> service.getId().equals(serviceDTO.getId()))
                    .findFirst()
                    .ifPresent(service -> serviceDTO.setImageURL(imageUtil.getImage(service.getImageURL())));
        }
        return serviceDTOs;
    }

    @Override
    public void delete(UUID id) {
        if (salonServiceRepo.existsById(id)) {
            salonServiceRepo.deleteById(id);
        } else {
            throw new RuntimeException("Salon service does not exist");
        }
    }

    @Override
    @Transactional
    public void update(SalonServiceDTO<String> salonServiceDTO) {
        if (salonServiceRepo.existsById(salonServiceDTO.getId())) {
            SalonService existingService = salonServiceRepo.findById(salonServiceDTO.getId()).get();
            // Map DTO to entity, preserving the existing image if not updated
            SalonService updatedService = modelMapper.map(salonServiceDTO, SalonService.class);
            updatedService.setImageURL(existingService.getImageURL()); // Retain original image unless updated separately
            salonServiceRepo.save(updatedService);
        } else {
            throw new RuntimeException("Salon service does not exist");
        }
    }
}

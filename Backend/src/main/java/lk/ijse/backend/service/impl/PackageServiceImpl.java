package lk.ijse.backend.service.impl;


import jakarta.transaction.Transactional;
import lk.ijse.backend.dto.PackageDTO;
import lk.ijse.backend.entity.Packages;
import lk.ijse.backend.entity.SalonService;
import lk.ijse.backend.enums.ImageType;
import lk.ijse.backend.repo.PackageRepo;
import lk.ijse.backend.service.PackageService;
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
public class PackageServiceImpl implements PackageService {
    private static final Logger logger = LoggerFactory.getLogger(PackageServiceImpl.class);

    @Autowired
    private PackageRepo packageRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ImageUtil imageUtil;

    @Override
    @Transactional
    public PackageDTO<String> save(PackageDTO<String> packageDTO, MultipartFile file) {
        String base64Image = imageUtil.saveImage(ImageType.PACKAGE, file); // Assuming ImageType.PACKAGE exists
        logger.info("Base64 image: {}", base64Image);

        Packages packageEntity = modelMapper.map(packageDTO, Packages.class);
        packageEntity.setImageURL(base64Image); // Assuming image is stored as Base64 in entity

        try {
            Packages savedPackage = packageRepo.save(packageEntity);
            PackageDTO<String> savedDTO = modelMapper.map(savedPackage, PackageDTO.class);
            savedDTO.setImageURL(base64Image);
            return savedDTO;
        } catch (Exception e) {
            logger.error("Failed to save package: {}", packageEntity, e);
            throw new RuntimeException("Failed to save package");
        }
    }

    @Override
    public List<PackageDTO<String>> getAll() {
        List<Packages> packages = packageRepo.findAll();
        List<PackageDTO<String>> packageDTOs = modelMapper.map(packages, new TypeToken<List<PackageDTO<String>>>() {}.getType());
        for (PackageDTO<String> packageDTO : packageDTOs) {
            packages.stream()
                    .filter(pkg -> pkg.getId().equals(packageDTO.getId()))
                    .findFirst()
                    .ifPresent(pkg -> packageDTO.setImageURL(imageUtil.getImage(pkg.getImageURL())));
        }
        return packageDTOs;
    }

    @Override
    public void delete(UUID id) {
        if (packageRepo.existsById(id)) {
            packageRepo.deleteById(id);
        } else {
            throw new RuntimeException("Package does not exist");
        }
    }

    @Override
    @Transactional
    public void update(PackageDTO<String> packageDTO) {
        if (packageRepo.existsById(packageDTO.getId())) {
            Packages existingPackage = packageRepo.findById(packageDTO.getId()).get();
            // Map DTO to entity, preserving the existing image if not updated
            Packages updatedPackage = modelMapper.map(packageDTO, Packages.class);
            updatedPackage.setImageURL(existingPackage.getImageURL()); // Retain original image unless updated separately
            packageRepo.save(updatedPackage);
        } else {
            throw new RuntimeException("Package does not exist");
        }
    }
}

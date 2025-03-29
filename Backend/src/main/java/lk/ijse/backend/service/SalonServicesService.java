package lk.ijse.backend.service;



import lk.ijse.backend.dto.SalonServiceDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface SalonServicesService {

    SalonServiceDTO<String> save(SalonServiceDTO<String> salonServiceDTO, MultipartFile file);
    List<SalonServiceDTO<String>> getAll();
    void delete(UUID id);
    void update(SalonServiceDTO<String> salonServiceDTO);




}

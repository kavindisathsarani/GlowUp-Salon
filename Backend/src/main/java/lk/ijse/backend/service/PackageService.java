package lk.ijse.backend.service;



import lk.ijse.backend.dto.PackageDTO;
import lk.ijse.backend.dto.SalonServiceDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface PackageService {

    PackageDTO<String> save(PackageDTO<String> packageDTO, MultipartFile file);
    List<PackageDTO<String>> getAll();
    void delete(UUID id);
    void update(PackageDTO<String> packageDTO);




}

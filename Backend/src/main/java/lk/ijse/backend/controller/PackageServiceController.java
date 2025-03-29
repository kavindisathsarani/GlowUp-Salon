package lk.ijse.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lk.ijse.backend.dto.PackageDTO;
import lk.ijse.backend.service.impl.PackageServiceImpl;
import lk.ijse.backend.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/package")
@CrossOrigin
public class PackageServiceController {
    private static final Logger log = LoggerFactory.getLogger(PackageServiceController.class);

    @Autowired
    private PackageServiceImpl packageService;

    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil savePackage(@RequestPart("package") String packageJson, @RequestPart("file") MultipartFile file) {
        try {
            PackageDTO<String> packageDTO = new ObjectMapper().readValue(packageJson, PackageDTO.class);
            log.info("Received request to save package: {}", packageDTO.getName());
            packageDTO.setImageURL(file.getOriginalFilename()); // Temporary placeholder, actual Base64 set in service
            PackageDTO<String> savedPackage = packageService.save(packageDTO, file);
            log.info("Package saved successfully: {}", packageDTO.getName());
            return new ResponseUtil(201, "Package saved successfully", savedPackage);
        } catch (Exception e) {
            log.error("Error saving package", e);
            return new ResponseUtil(500, "Internal server error", null);
        }
    }

    @GetMapping(path = "/get-all")
    public ResponseUtil getAllPackages() {
        try {
            List<PackageDTO<String>> packages = packageService.getAll();
            return new ResponseUtil(200, "Packages retrieved successfully", packages);
        } catch (Exception e) {
            log.error("Error retrieving packages", e);
            return new ResponseUtil(500, "Internal server error", null);
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseUtil deletePackage(@PathVariable(value = "id") UUID id) {
        try {
            packageService.delete(id);
            return new ResponseUtil(200, "Package deleted", null);
        } catch (Exception e) {
            log.error("Error deleting package with id: {}", id, e);
            return new ResponseUtil(500, "Internal server error", null);
        }
    }

    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updatePackage(@RequestBody PackageDTO<String> packageDTO) {
        try {
            log.info("Received request to update package: {}", packageDTO.getName());
            packageService.update(packageDTO);
            log.info("Package updated successfully: {}", packageDTO.getName());
            return new ResponseUtil(200, "Package updated successfully", null);
        } catch (Exception e) {
            log.error("Error updating package", e);
            return new ResponseUtil(500, "Internal server error", null);
        }
    }
}

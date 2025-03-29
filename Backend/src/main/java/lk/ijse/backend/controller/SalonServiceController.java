package lk.ijse.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lk.ijse.backend.dto.SalonServiceDTO;
import lk.ijse.backend.service.impl.SalonServiceImpl;
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
@RequestMapping("/api/v1/salon-service")
@CrossOrigin
public class SalonServiceController {
    private static final Logger log = LoggerFactory.getLogger(SalonServiceController.class);

    @Autowired
    private SalonServiceImpl salonServiceService;

    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil saveSalonService(@RequestPart("service") String serviceJson, @RequestPart("file") MultipartFile file) {
        try {
            SalonServiceDTO<String> serviceDTO = new ObjectMapper().readValue(serviceJson, SalonServiceDTO.class);
            log.info("Received request to save salon service: {}", serviceDTO.getName());
            serviceDTO.setImageURL(file.getOriginalFilename()); // Temporary placeholder, actual Base64 set in service
            SalonServiceDTO<String> savedService = salonServiceService.save(serviceDTO, file);
            log.info("Salon service saved successfully: {}", serviceDTO.getName());
            return new ResponseUtil(201, "Salon service saved successfully", savedService);
        } catch (Exception e) {
            log.error("Error saving salon service", e);
            return new ResponseUtil(500, "Internal server error", null);
        }
    }

    @GetMapping(path = "/get-all")
    public ResponseUtil getAllSalonServices() {
        try {
            List<SalonServiceDTO<String>> services = salonServiceService.getAll();
            return new ResponseUtil(200, "Salon services retrieved successfully", services);
        } catch (Exception e) {
            log.error("Error retrieving salon services", e);
            return new ResponseUtil(500, "Internal server error", null);
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseUtil deleteSalonService(@PathVariable(value = "id") UUID id) {
        try {
            salonServiceService.delete(id);
            return new ResponseUtil(200, "Salon service deleted", null);
        } catch (Exception e) {
            log.error("Error deleting salon service with id: {}", id, e);
            return new ResponseUtil(500, "Internal server error", null);
        }
    }

    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateSalonService(@RequestBody SalonServiceDTO<String> serviceDTO) {
        try {
            log.info("Received request to update salon service: {}", serviceDTO.getName());
            salonServiceService.update(serviceDTO);
            log.info("Salon service updated successfully: {}", serviceDTO.getName());
            return new ResponseUtil(200, "Salon service updated successfully", null);
        } catch (Exception e) {
            log.error("Error updating salon service", e);
            return new ResponseUtil(500, "Internal server error", null);
        }
    }

}

package lk.ijse.backend.controller;

import lk.ijse.backend.dto.serviceAppointmentDTO;
import lk.ijse.backend.service.ServiceAppointmentService;
import lk.ijse.backend.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/serviceAppointment")
@CrossOrigin(origins = "*")
public class ServiceAppointmentController {
    @Autowired
    private ServiceAppointmentService serviceAppointmentService;

    @PostMapping("/save")
    public ResponseUtil saveServiceAppointment(@RequestBody serviceAppointmentDTO dto) {
        serviceAppointmentService.save(dto);
        return new ResponseUtil(200, "Service appointment saved successfully", null);
    }

    @GetMapping("/getAll")
    public ResponseUtil getAllServiceAppointments() {
        return new ResponseUtil(200, "All service appointments retrieved successfully", serviceAppointmentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseUtil> getServiceAppointmentById(@PathVariable int id) {
        serviceAppointmentDTO dto = serviceAppointmentService.getById(id);
        return ResponseEntity.ok(new ResponseUtil(200, "Service appointment retrieved successfully", dto));
    }

    @PutMapping("/update")
    public ResponseUtil updateServiceAppointment(@RequestBody serviceAppointmentDTO dto) {
        serviceAppointmentService.update(dto);
        return new ResponseUtil(200, "Service appointment updated successfully", null);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseUtil deleteServiceAppointment(@PathVariable int id) {
        serviceAppointmentService.delete(id);
        return new ResponseUtil(200, "Service appointment deleted successfully", null);
    }
}

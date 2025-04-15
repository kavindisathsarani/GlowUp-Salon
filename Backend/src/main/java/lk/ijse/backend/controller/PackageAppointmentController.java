package lk.ijse.backend.controller;

import lk.ijse.backend.dto.packageAppointmentDTO;
import lk.ijse.backend.service.PackageAppointmentService;
import lk.ijse.backend.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/packageAppointment")
@CrossOrigin(origins = "*")
public class PackageAppointmentController {
    @Autowired
    private PackageAppointmentService appointmentService;

    @PostMapping("/save")
    public ResponseUtil saveAppointment(@RequestBody packageAppointmentDTO dto) {
        appointmentService.save(dto);
        return new ResponseUtil(200, "Appointment saved successfully", null);
    }

    @GetMapping("/getAll")
    public ResponseUtil getAllAppointments() {
        return new ResponseUtil(200, "All appointments retrieved successfully", appointmentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getAppointmentById(@PathVariable int id) {
        packageAppointmentDTO dto = appointmentService.getById(id);
        return ResponseEntity.ok(new ResponseUtil(200, "Appointment retrieved successfully", dto));
    }

    @PutMapping("/update")
    public ResponseUtil updateAppointment(@RequestBody packageAppointmentDTO dto) {
        appointmentService.update(dto);
        return new ResponseUtil(200, "Appointment updated successfully", null);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseUtil deleteAppointment(@PathVariable int id) {
        appointmentService.delete(id);
        return new ResponseUtil(200, "Appointment deleted successfully", null);
    }
}

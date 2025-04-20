package lk.ijse.backend.controller;

import lk.ijse.backend.dto.serviceAppointmentDTO;
import lk.ijse.backend.service.EmailService;
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

    @Autowired
    private EmailService emailService;

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
        // Save the updated appointment
        serviceAppointmentService.update(dto);

        // Check if the status is "Confirmed" and send confirmation email
        if (dto.getStatus() != null && dto.getStatus().equalsIgnoreCase("Confirmed")) {
            boolean emailSent = emailService.sendAppointmentConfirmationEmail(dto);
            if (emailSent) {
                return new ResponseUtil(200, "Service appointment confirmed and confirmation email sent", null);
            } else {
                return new ResponseUtil(200, "Service appointment confirmed but failed to send email", null);
            }
        }

        return new ResponseUtil(200, "Service appointment updated successfully", null);
    }

    @PutMapping("/confirm/{id}")
    public ResponseUtil confirmServiceAppointment(@PathVariable int id) {
        // Get the appointment
        serviceAppointmentDTO dto = serviceAppointmentService.getById(id);

        // Update status to "Confirmed"
        dto.setStatus("Confirmed");
        serviceAppointmentService.update(dto);

        // Send confirmation email
        boolean emailSent = emailService.sendAppointmentConfirmationEmail(dto);

        if (emailSent) {
            return new ResponseUtil(200, "Service appointment confirmed and confirmation email sent", null);
        } else {
            return new ResponseUtil(200, "Service appointment confirmed but failed to send email", null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseUtil deleteServiceAppointment(@PathVariable int id) {
        serviceAppointmentService.delete(id);
        return new ResponseUtil(200, "Service appointment deleted successfully", null);
    }

    @PutMapping("/cancel/{id}")
    public ResponseUtil cancelServiceAppointment(@PathVariable int id) {
        // Get the appointment
        serviceAppointmentDTO dto = serviceAppointmentService.getById(id);

        // Update status to "Cancelled"
        dto.setStatus("Cancelled");
        serviceAppointmentService.update(dto);

        // Send cancellation email
        boolean emailSent = emailService.sendAppointmentCancellationEmail(dto);

        if (emailSent) {
            return new ResponseUtil(200, "Service appointment cancelled and cancellation email sent", null);
        } else {
            return new ResponseUtil(200, "Service appointment cancelled but failed to send email", null);
        }



    }
   /* @Autowired
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
    }*/
}

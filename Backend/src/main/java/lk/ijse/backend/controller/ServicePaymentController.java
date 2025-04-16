package lk.ijse.backend.controller;

import lk.ijse.backend.dto.ServicePaymentDTO;
import lk.ijse.backend.service.ServicePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/servicePayment")
@CrossOrigin
public class ServicePaymentController {
    @Autowired
    private ServicePaymentService paymentService;

    /**
     * Create a new payment
     * @param paymentDTO The payment data transfer object
     * @return ResponseEntity containing the created payment
     */
    @PostMapping("/create")
    public ResponseEntity<ServicePaymentDTO> createPayment(@RequestBody ServicePaymentDTO paymentDTO) {
        return new ResponseEntity<>(paymentService.createPayment(paymentDTO), HttpStatus.CREATED);
    }

    /**
     * Get payment by ID
     * @param paymentId The payment ID
     * @return ResponseEntity containing the payment
     */
    @GetMapping("/{paymentId}")
    public ResponseEntity<ServicePaymentDTO> getPaymentById(@PathVariable int paymentId) {
        return ResponseEntity.ok(paymentService.getPaymentById(paymentId));
    }

    /**
     * Get payment by appointment ID
     * @param appointmentId The appointment ID
     * @return ResponseEntity containing the payment
     */
    @GetMapping("/appointment/{appointmentId}")
    public ResponseEntity<ServicePaymentDTO> getPaymentByAppointmentId(@PathVariable int appointmentId) {
        return ResponseEntity.ok(paymentService.getPaymentByAppointmentId(appointmentId));
    }

    /**
     * Get all payments
     * @return ResponseEntity containing list of all payments
     */
    @GetMapping("/all")
    public ResponseEntity<List<ServicePaymentDTO>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    /**
     * Update payment status
     * @param paymentId The payment ID
     * @param status The new status
     * @return ResponseEntity containing the updated payment
     */
    @PutMapping("/update-status/{paymentId}")
    public ResponseEntity<ServicePaymentDTO> updatePaymentStatus(
            @PathVariable int paymentId,
            @RequestParam String status) {
        return ResponseEntity.ok(paymentService.updatePaymentStatus(paymentId, status));
    }
}

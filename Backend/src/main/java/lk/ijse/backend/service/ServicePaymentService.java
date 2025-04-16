package lk.ijse.backend.service;

import lk.ijse.backend.dto.ServicePaymentDTO;

import java.util.List;

public interface ServicePaymentService {
    /**
     * Create a new payment
     * @param paymentDTO The payment data transfer object
     * @return The created payment DTO
     */
    ServicePaymentDTO createPayment(ServicePaymentDTO paymentDTO);

    /**
     * Get payment by ID
     * @param paymentId The payment ID
     * @return The payment DTO
     */
    ServicePaymentDTO getPaymentById(int paymentId);

    /**
     * Get payment by appointment ID
     * @param appointmentId The appointment ID
     * @return The payment DTO associated with the appointment
     */
    ServicePaymentDTO getPaymentByAppointmentId(int appointmentId);

    /**
     * Get all payments
     * @return List of all payment DTOs
     */
    List<ServicePaymentDTO> getAllPayments();

    /**
     * Update payment status
     * @param paymentId The payment ID
     * @param status The new status
     * @return The updated payment DTO
     */
    ServicePaymentDTO updatePaymentStatus(int paymentId, String status);
}

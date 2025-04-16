package lk.ijse.backend.service.impl;

import lk.ijse.backend.dto.ServicePaymentDTO;
import lk.ijse.backend.entity.ServiceAppointment;
import lk.ijse.backend.entity.ServicePayment;
import lk.ijse.backend.repo.ServiceAppointmentsRepo;
import lk.ijse.backend.repo.ServicePaymentRepo;
import lk.ijse.backend.service.ServicePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicePaymentServiceImpl implements ServicePaymentService {
    @Autowired
    private ServicePaymentRepo paymentRepository;

    @Autowired
    private ServiceAppointmentsRepo appointmentRepository;

    // Create a new payment
    @Override
    public ServicePaymentDTO createPayment(ServicePaymentDTO paymentDTO) {
        ServiceAppointment appointment = appointmentRepository.findById(paymentDTO.getServiceAppointmentId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Appointment not found with ID: " + paymentDTO.getServiceAppointmentId()));

        // Check if payment already exists for this appointment
        if (paymentRepository.findByServiceAppointment_ServiceAppointmentId(paymentDTO.getServiceAppointmentId()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Payment already exists for appointment ID: " + paymentDTO.getServiceAppointmentId());
        }

        ServicePayment payment = new ServicePayment();
        payment.setServiceAppointment(appointment);
        payment.setAmount(paymentDTO.getAmount());
        payment.setPaymentDate(LocalDateTime.now());
        payment.setPaymentMethod(paymentDTO.getPaymentMethod());
        payment.setTransactionId(paymentDTO.getTransactionId());
        payment.setStatus(paymentDTO.getStatus());

        // Update appointment status to PAID
        appointment.setStatus("PAID");
        appointmentRepository.save(appointment);

        ServicePayment savedPayment = paymentRepository.save(payment);
        return convertToDTO(savedPayment);
    }

    // Get payment by ID
    @Override
    public ServicePaymentDTO getPaymentById(int paymentId) {
        ServicePayment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Payment not found with ID: " + paymentId));

        return convertToDTO(payment);
    }

    // Get payment by appointment ID
    @Override
    public ServicePaymentDTO getPaymentByAppointmentId(int appointmentId) {
        ServicePayment payment = paymentRepository.findByServiceAppointment_ServiceAppointmentId(appointmentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Payment not found for appointment ID: " + appointmentId));

        return convertToDTO(payment);
    }

    // Get all payments
    @Override
    public List<ServicePaymentDTO> getAllPayments() {
        return paymentRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Update payment status
    @Override
    public ServicePaymentDTO updatePaymentStatus(int paymentId, String status) {
        ServicePayment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Payment not found with ID: " + paymentId));

        payment.setStatus(status);
        return convertToDTO(paymentRepository.save(payment));
    }

    // Helper method to convert entity to DTO
    private ServicePaymentDTO convertToDTO(ServicePayment payment) {
        return new ServicePaymentDTO(
                payment.getPaymentId(),
                payment.getServiceAppointment().getServiceAppointmentId(),
                payment.getAmount(),
                payment.getPaymentDate(),
                payment.getPaymentMethod(),
                payment.getTransactionId(),
                payment.getStatus()
        );
    }
}

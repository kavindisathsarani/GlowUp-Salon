package lk.ijse.backend.service;

import lk.ijse.backend.dto.serviceAppointmentDTO;

public interface EmailService {
    /**
     * Sends an appointment confirmation email to the user
     *
     * @param appointmentDTO The service appointment details
     * @return true if the email was sent successfully, false otherwise
     */
    boolean sendAppointmentConfirmationEmail(serviceAppointmentDTO appointmentDTO);

    /**
     * Sends an appointment cancellation email to the user
     *
     * @param appointmentDTO The service appointment details
     * @return true if the email was sent successfully, false otherwise
     */
    boolean sendAppointmentCancellationEmail(serviceAppointmentDTO appointmentDTO);
}

package lk.ijse.backend.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lk.ijse.backend.dto.serviceAppointmentDTO;
import lk.ijse.backend.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String senderEmail;

    @Value("${application.name:GlowUp Salon}")
    private String applicationName;

    @Override
    public boolean sendAppointmentConfirmationEmail(serviceAppointmentDTO appointmentDTO) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            // Using the userEmail from the DTO directly as recipient
            String recipientEmail = appointmentDTO.getUserEmail();

            helper.setFrom(senderEmail);
            helper.setTo(recipientEmail);
            helper.setSubject(applicationName + " - Appointment Confirmation");

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

            String appointmentDate = appointmentDTO.getBookingDate().format(dateFormatter);
            String appointmentTime = appointmentDTO.getAppointmentTime().format(timeFormatter);

            String emailContent =
                    "<html>" +
                            "<body style='font-family: Arial, sans-serif;'>" +
                            "<div style='background-color: #f8f9fa; padding: 20px; border-radius: 5px;'>" +
                            "<h2 style='color: #e83e8c;'>Appointment Confirmation</h2>" +
                            "<p>Dear Customer,</p>" +
                            "<p>Your appointment has been confirmed successfully!</p>" +
                            "<div style='background-color: white; padding: 15px; border-radius: 5px; margin: 15px 0;'>" +
                            "<p><strong>Appointment Details:</strong></p>" +
                            "<ul style='list-style-type: none; padding-left: 0;'>" +
                            "<li><strong>Service:</strong> " + appointmentDTO.getServiceName() + "</li>" +
                            "<li><strong>Date:</strong> " + appointmentDate + "</li>" +
                            "<li><strong>Time:</strong> " + appointmentTime + "</li>" +
                            "<li><strong>Price:</strong> Rs. " + appointmentDTO.getPrice() + "</li>" +
                            "</ul>" +
                            "</div>" +
                            "<p>If you need to make any changes to your appointment, please contact us as soon as possible.</p>" +
                            "<p>Thank you for choosing " + applicationName + "!</p>" +
                            "<p>Best regards,<br>" +
                            "The " + applicationName + " Team</p>" +
                            "</div>" +
                            "</body>" +
                            "</html>";

            helper.setText(emailContent, true);
            mailSender.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean sendAppointmentCancellationEmail(serviceAppointmentDTO appointmentDTO) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            // Using the userEmail from the DTO directly as recipient
            String recipientEmail = appointmentDTO.getUserEmail();

            helper.setFrom(senderEmail);
            helper.setTo(recipientEmail);
            helper.setSubject(applicationName + " - Appointment Cancellation");

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

            String appointmentDate = appointmentDTO.getBookingDate().format(dateFormatter);
            String appointmentTime = appointmentDTO.getAppointmentTime().format(timeFormatter);

            String emailContent =
                    "<html>" +
                            "<body style='font-family: Arial, sans-serif;'>" +
                            "<div style='background-color: #f8f9fa; padding: 20px; border-radius: 5px;'>" +
                            "<h2 style='color: #dc3545;'>Appointment Cancellation</h2>" +
                            "<p>Dear Customer,</p>" +
                            "<p>Your appointment has been cancelled as requested.</p>" +
                            "<div style='background-color: white; padding: 15px; border-radius: 5px; margin: 15px 0;'>" +
                            "<p><strong>Cancelled Appointment Details:</strong></p>" +
                            "<ul style='list-style-type: none; padding-left: 0;'>" +
                            "<li><strong>Service:</strong> " + appointmentDTO.getServiceName() + "</li>" +
                            "<li><strong>Date:</strong> " + appointmentDate + "</li>" +
                            "<li><strong>Time:</strong> " + appointmentTime + "</li>" +
                            "<li><strong>Price:</strong> Rs. " + appointmentDTO.getPrice() + "</li>" +
                            "</ul>" +
                            "</div>" +
                            "<p>If you would like to reschedule, please visit our website or contact us directly.</p>" +
                            "<p>Thank you for your understanding.</p>" +
                            "<p>Best regards,<br>" +
                            "The " + applicationName + " Team</p>" +
                            "</div>" +
                            "</body>" +
                            "</html>";

            helper.setText(emailContent, true);
            mailSender.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

}

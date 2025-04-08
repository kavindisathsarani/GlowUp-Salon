package lk.ijse.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class serviceAppointmentDTO {
    private int serviceAppointmentId;
    private String userEmail;
    private String serviceName;
    private BigDecimal price;  // Price comes from service table
    private LocalDate bookingDate;
    private LocalTime appointmentTime;
    private String status;

    public serviceAppointmentDTO() {
    }

    public serviceAppointmentDTO(int serviceAppointmentId, String userEmail, String serviceName, BigDecimal price, LocalDate bookingDate, LocalTime appointmentTime, String status) {
        this.serviceAppointmentId = serviceAppointmentId;
        this.userEmail = userEmail;
        this.serviceName = serviceName;
        this.price = price;
        this.bookingDate = bookingDate;
        this.appointmentTime = appointmentTime;
        this.status = status;
    }

    public int getServiceAppointmentId() {
        return serviceAppointmentId;
    }

    public void setServiceAppointmentId(int serviceAppointmentId) {
        this.serviceAppointmentId = serviceAppointmentId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public LocalTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

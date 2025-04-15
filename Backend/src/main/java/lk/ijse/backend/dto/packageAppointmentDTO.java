package lk.ijse.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class packageAppointmentDTO {
    private int packageAppointmentId;
    private String userEmail;
    private String packageName;
    private BigDecimal price; // Price from the package table
    private LocalDate bookingDate;
    private LocalTime appointmentTime;
    private String status;
    private String notes;

    public packageAppointmentDTO() {
    }

    public packageAppointmentDTO(int packageAppointmentId, String userEmail, String packageName, BigDecimal price, LocalDate bookingDate, LocalTime appointmentTime, String status, String notes) {
        this.packageAppointmentId = packageAppointmentId;
        this.userEmail = userEmail;
        this.packageName = packageName;
        this.price = price;
        this.bookingDate = bookingDate;
        this.appointmentTime = appointmentTime;
        this.status = status;
        this.notes = notes;
    }

    public int getPackageAppointmentId() {
        return packageAppointmentId;
    }

    public void setPackageAppointmentId(int packageAppointmentId) {
        this.packageAppointmentId = packageAppointmentId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}

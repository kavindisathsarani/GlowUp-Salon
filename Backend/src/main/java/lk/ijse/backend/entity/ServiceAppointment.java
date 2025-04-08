package lk.ijse.backend.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "service_appointments")
public class ServiceAppointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int serviceAppointmentId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private SalonService service;

    @Column(nullable = false)
    private LocalDate bookingDate;

    @Column(nullable = false)
    private LocalTime appointmentTime;

    @Column(name = "status", length = 20)
    private String status; // PENDING, CONFIRMED, COMPLETED, CANCELLED

    public ServiceAppointment() {
    }

    public ServiceAppointment(int serviceAppointmentId, User user, SalonService service, LocalDate bookingDate, LocalTime appointmentTime, String status) {
        this.serviceAppointmentId = serviceAppointmentId;
        this.user = user;
        this.service = service;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SalonService getService() {
        return service;
    }

    public void setService(SalonService service) {
        this.service = service;
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

package lk.ijse.backend.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "package_appointments")
public class PackageAppointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int packageAppointmentId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "package_id", nullable = false)
    private Packages aPackage;

    @Column(nullable = false)
    private LocalDate bookingDate;

    @Column(nullable = false)
    private LocalTime appointmentTime;

    @Column(name = "status", length = 20)
    private String status; // PENDING, CONFIRMED, COMPLETED, CANCELLED

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes; // Any special requests or notes by the user

    public PackageAppointment() {
    }

    public PackageAppointment(int packageAppointmentId, User user, Packages aPackage, LocalDate bookingDate, LocalTime appointmentTime, String status, String notes) {
        this.packageAppointmentId = packageAppointmentId;
        this.user = user;
        this.aPackage = aPackage;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Packages getaPackage() {
        return aPackage;
    }

    public void setaPackage(Packages aPackage) {
        this.aPackage = aPackage;
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

package lk.ijse.backend.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "service_payments")
public class ServicePayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentId;

    @OneToOne
    @JoinColumn(name = "service_appointment_id", nullable = false, unique = true)
    private ServiceAppointment serviceAppointment;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDateTime paymentDate;

    @Column(length = 50)
    private String paymentMethod; // CREDIT_CARD, DEBIT_CARD, CASH, etc.

    @Column(length = 100)
    private String transactionId;

    @Column(length = 20)
    private String status; // PENDING, COMPLETED, FAILED, REFUNDED

    // Default constructor
    public ServicePayment() {
    }

    // Parameterized constructor
    public ServicePayment(int paymentId, ServiceAppointment serviceAppointment, BigDecimal amount,
                          LocalDateTime paymentDate, String paymentMethod, String transactionId, String status) {
        this.paymentId = paymentId;
        this.serviceAppointment = serviceAppointment;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.transactionId = transactionId;
        this.status = status;
    }

    // Getters and Setters
    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public ServiceAppointment getServiceAppointment() {
        return serviceAppointment;
    }

    public void setServiceAppointment(ServiceAppointment serviceAppointment) {
        this.serviceAppointment = serviceAppointment;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

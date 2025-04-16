package lk.ijse.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ServicePaymentDTO {
    private int paymentId;
    private int serviceAppointmentId;
    private BigDecimal amount;
    private LocalDateTime paymentDate;
    private String paymentMethod;
    private String transactionId;
    private String status;

    // Default constructor
    public ServicePaymentDTO() {
    }

    // Parameterized constructor
    public ServicePaymentDTO(int paymentId, int serviceAppointmentId, BigDecimal amount,
                             LocalDateTime paymentDate, String paymentMethod, String transactionId, String status) {
        this.paymentId = paymentId;
        this.serviceAppointmentId = serviceAppointmentId;
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

    public int getServiceAppointmentId() {
        return serviceAppointmentId;
    }

    public void setServiceAppointmentId(int serviceAppointmentId) {
        this.serviceAppointmentId = serviceAppointmentId;
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

package lk.ijse.backend.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class PackageDTO<T> implements Serializable {
    private UUID id;
    private T imageURL;
    private String name;
    private String description;
    private BigDecimal totalPrice;
    private Integer totalDurationMinutes;
    private Integer validityDays; // Unique field: Number of days the package is valid for after purchase
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PackageDTO() {
    }

    public PackageDTO(UUID id, T imageURL, String name, String description, BigDecimal totalPrice, Integer totalDurationMinutes, Integer validityDays, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.imageURL = imageURL;
        this.name = name;
        this.description = description;
        this.totalPrice = totalPrice;
        this.totalDurationMinutes = totalDurationMinutes;
        this.validityDays = validityDays;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public T getImageURL() {
        return imageURL;
    }

    public void setImageURL(T imageURL) {
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getTotalDurationMinutes() {
        return totalDurationMinutes;
    }

    public void setTotalDurationMinutes(Integer totalDurationMinutes) {
        this.totalDurationMinutes = totalDurationMinutes;
    }

    public Integer getValidityDays() {
        return validityDays;
    }

    public void setValidityDays(Integer validityDays) {
        this.validityDays = validityDays;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}

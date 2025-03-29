package lk.ijse.backend.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class SalonServiceDTO <T> implements Serializable {
    private UUID id;
    private T imageURL;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer durationMinutes;
    private LocalDateTime createdAt;

    // Getters and Setters


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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // Constructors


    public SalonServiceDTO(UUID id, T imageURL, String name, String description, BigDecimal price, Integer durationMinutes, LocalDateTime createdAt) {
        this.id = id;
        this.imageURL = imageURL;
        this.name = name;
        this.description = description;
        this.price = price;
        this.durationMinutes = durationMinutes;
        this.createdAt = createdAt;
    }

    public SalonServiceDTO() {
    }
}

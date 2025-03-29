package lk.ijse.backend.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "salonService")
public class SalonService {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false, length = 100) // VARCHAR(100) NOT NULL
    private String name;

    @Column(name = "description", columnDefinition = "TEXT") // TEXT
    private String description;

    @Column(name = "price", nullable = false, precision = 10) // DECIMAL(10, 2) NOT NULL
    private BigDecimal price;

    @Column(name = "duration_minutes", nullable = false) // INT NOT NULL
    private Integer durationMinutes;

    @Column(name = "image_path", length = 255) // VARCHAR(255) for image path
    private String imageURL;

    @Column(name = "created_at", nullable = false, updatable = false) // TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    private LocalDateTime createdAt;

    // Getters and Setters


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


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


    // Constructors

    public SalonService() {
        // Default constructor required by JPA
    }

    public SalonService(UUID id, String name, String description, BigDecimal price, Integer durationMinutes, String imageURL, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.durationMinutes = durationMinutes;
        this.imageURL = imageURL;
        this.createdAt = createdAt;

    }

    // Pre-persist to set created_at and serviceUuid if not set
    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
    }
}

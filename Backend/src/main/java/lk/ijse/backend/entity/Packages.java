package lk.ijse.backend.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "package")
public class Packages {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "image_url", length = 255) // VARCHAR(255) for image URL/path
    private String imageURL; // Changed from generic T to String for simplicity

    @Column(name = "name", nullable = false, length = 100) // VARCHAR(100) NOT NULL
    private String name;

    @Column(name = "description", columnDefinition = "TEXT") // TEXT
    private String description;

    @Column(name = "total_price", nullable = false, precision = 10) // DECIMAL(10, 2) NOT NULL
    private BigDecimal totalPrice;

    @Column(name = "total_duration_minutes", nullable = false) // INT NOT NULL
    private Integer totalDurationMinutes;

    @Column(name = "validity_days", nullable = false) // INT NOT NULL
    private Integer validityDays;

    @Column(name = "created_at", nullable = false, updatable = false) // TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false) // TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
    private LocalDateTime updatedAt;

    // Getters and Setters

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

    // Constructors

    public Packages() {
        // Default constructor required by JPA
    }

    public Packages(UUID id, String imageURL, String name, String description, BigDecimal totalPrice,
                    Integer totalDurationMinutes, Integer validityDays, LocalDateTime createdAt, LocalDateTime updatedAt) {
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

    // Pre-persist to set created_at and updated_at if not set
    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
        if (this.updatedAt == null) {
            this.updatedAt = LocalDateTime.now();
        }
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
    }

    // Pre-update to set updated_at
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}

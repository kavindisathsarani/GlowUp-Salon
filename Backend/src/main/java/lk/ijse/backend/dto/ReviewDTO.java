package lk.ijse.backend.dto;

import java.time.LocalDateTime;

public class ReviewDTO {
    private int reviewId;
    private int customerId;
    private String customerName;
    private int rating;
    private String comment;
    private LocalDateTime createdAt;

    public ReviewDTO() {
    }

    public ReviewDTO(int reviewId, int customerId, String customerName, int rating, String comment, LocalDateTime createdAt) {
        this.reviewId = reviewId;
        this.customerId = customerId;
        this.customerName = customerName;

        this.rating = rating;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }





    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

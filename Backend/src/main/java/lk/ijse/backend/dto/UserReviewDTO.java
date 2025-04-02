package lk.ijse.backend.dto;

import java.time.LocalDateTime;

public class UserReviewDTO {
    private int reviewId;
    private String email;
    private String userName;
    private int rating;
    private String comment;
    private LocalDateTime createdAt;

    public UserReviewDTO() {
    }

    public UserReviewDTO(int reviewId, String email, String userName, int rating, String comment, LocalDateTime createdAt) {
        this.reviewId = reviewId;
        this.email = email;
        this.userName = userName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

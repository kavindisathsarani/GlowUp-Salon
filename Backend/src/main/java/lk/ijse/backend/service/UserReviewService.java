package lk.ijse.backend.service;

import lk.ijse.backend.dto.UserReviewDTO;

import java.util.List;

public interface UserReviewService {
    void save(UserReviewDTO userReviewDTO);
    List<UserReviewDTO> getAll();
    UserReviewDTO getById(int id);
    void update(UserReviewDTO userReviewDTO);
    void delete(int id);
}

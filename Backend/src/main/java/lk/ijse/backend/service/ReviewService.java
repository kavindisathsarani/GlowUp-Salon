package lk.ijse.backend.service;

import lk.ijse.backend.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    void save(ReviewDTO reviewDTO);
    List<ReviewDTO> getAll();
    void update(ReviewDTO reviewDTO);
    void delete(int id);
    ReviewDTO getById(int id);
}

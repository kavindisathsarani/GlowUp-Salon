package lk.ijse.backend.service.impl;

import lk.ijse.backend.dto.UserReviewDTO;
import lk.ijse.backend.entity.User;
import lk.ijse.backend.entity.UserReview;
import lk.ijse.backend.repo.UserRepository;
import lk.ijse.backend.repo.UserReviewRepo;
import lk.ijse.backend.service.UserReviewService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserReviewServiceImpl implements UserReviewService {
    @Autowired
    private UserReviewRepo userReviewRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void save(UserReviewDTO userReviewDTO) {
        if (userReviewDTO.getReviewId() > 0 && userReviewRepo.existsById(userReviewDTO.getReviewId())) {
            throw new RuntimeException("Review already exists");
        }

        // Map DTO to entity
        UserReview userReview = new UserReview();
        userReview.setRating(userReviewDTO.getRating());
        userReview.setComment(userReviewDTO.getComment());

        // Fetch and set User by email - fixed to handle non-Optional return
        User user = userRepo.findByEmail(userReviewDTO.getEmail());
        if (user == null) {
            throw new RuntimeException("User with email " + userReviewDTO.getEmail() + " not found");
        }
        userReview.setUser(user);

        userReviewRepo.save(userReview);
    }

    @Override
    public List<UserReviewDTO> getAll() {
        List<UserReview> userReviews = userReviewRepo.findAll();
        List<UserReviewDTO> userReviewDTOs = modelMapper.map(userReviews, new TypeToken<List<UserReviewDTO>>() {}.getType());

        // Set email and username for each DTO since ModelMapper won't handle this mapping automatically
        for (int i = 0; i < userReviews.size(); i++) {
            userReviewDTOs.get(i).setEmail(userReviews.get(i).getUser().getEmail()); // Assuming User has getEmail() method
            userReviewDTOs.get(i).setUserName(userReviews.get(i).getUser().getName()); // Assuming User has getName() method
        }

        return userReviewDTOs;
    }

    @Override
    public UserReviewDTO getById(int id) {
        Optional<UserReview> optionalUserReview = userReviewRepo.findById(id);
        if (optionalUserReview.isPresent()) {
            UserReview userReview = optionalUserReview.get();
            UserReviewDTO dto = modelMapper.map(userReview, UserReviewDTO.class);
            dto.setEmail(userReview.getUser().getEmail()); // Assuming User has getEmail() method
            dto.setUserName(userReview.getUser().getName()); // Assuming User has getName() method
            return dto;
        }
        throw new RuntimeException("Review not found");
    }

    @Override
    public void update(UserReviewDTO userReviewDTO) {
        if (!userReviewRepo.existsById(userReviewDTO.getReviewId())) {
            throw new RuntimeException("Review does not exist");
        }

        // Get existing review
        UserReview userReview = userReviewRepo.findById(userReviewDTO.getReviewId()).get();

        // Update fields
        userReview.setRating(userReviewDTO.getRating());
        userReview.setComment(userReviewDTO.getComment());

        // Check if user is changed by comparing emails
        if (!userReview.getUser().getEmail().equals(userReviewDTO.getEmail())) {
            // Fixed to handle non-Optional return
            User user = userRepo.findByEmail(userReviewDTO.getEmail());
            if (user == null) {
                throw new RuntimeException("User with email " + userReviewDTO.getEmail() + " not found");
            }
            userReview.setUser(user);
        }

        userReviewRepo.save(userReview);
    }

    @Override
    public void delete(int id) {
        if (!userReviewRepo.existsById(id)) {
            throw new RuntimeException("Review with ID " + id + " does not exist.");
        }
        userReviewRepo.deleteById(id);
    }
}

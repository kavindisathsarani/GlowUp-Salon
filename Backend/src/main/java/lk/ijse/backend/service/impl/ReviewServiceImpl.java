package lk.ijse.backend.service.impl;

import lk.ijse.backend.dto.ReviewDTO;
import lk.ijse.backend.entity.Customer;
import lk.ijse.backend.entity.Reviews;
import lk.ijse.backend.entity.SalonService;
import lk.ijse.backend.repo.CustomerRepo;
import lk.ijse.backend.repo.ReviewRepo;
import lk.ijse.backend.repo.SalonServiceRepo;
import lk.ijse.backend.service.ReviewService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepo reviewRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void save(ReviewDTO reviewDTO) {
        if (reviewRepo.existsById(reviewDTO.getReviewId())) {
            throw new RuntimeException("Review already exists");
        }

        // Map DTO to entity
        Reviews review = modelMapper.map(reviewDTO, Reviews.class);

        // Fetch and set Customer, maintaining bidirectional relationship
        Customer customer = customerRepo.findById(reviewDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer with ID " + reviewDTO.getCustomerId() + " not found"));
        review.setCustomer(customer);
        customer.getReviews().add(review); // Add review to customer's list

        reviewRepo.save(review);
    }

    @Override
    public List<ReviewDTO> getAll() {
        return modelMapper.map(reviewRepo.findAll(), new TypeToken<List<ReviewDTO>>() {}.getType());
    }

    @Override
    public ReviewDTO getById(int id) {
        Optional<Reviews> optionalReview = reviewRepo.findById(id);
        if (optionalReview.isPresent()) {
            return modelMapper.map(optionalReview.get(), ReviewDTO.class);
        }
        throw new RuntimeException("Review not found");
    }

    @Override
    public void update(ReviewDTO reviewDTO) {
        if (reviewRepo.existsById(reviewDTO.getReviewId())) {
            // Map DTO to entity
            Reviews review = modelMapper.map(reviewDTO, Reviews.class);

            // Fetch and set Customer
            Customer customer = customerRepo.findById(reviewDTO.getCustomerId())
                    .orElseThrow(() -> new RuntimeException("Customer with ID " + reviewDTO.getCustomerId() + " not found"));
            review.setCustomer(customer);
            // Note: No need to add to customer.getReviews() since the review already exists

            reviewRepo.save(review);
        } else {
            throw new RuntimeException("Review does not exist");
        }
    }

    @Override
    public void delete(int id) {
        if (!reviewRepo.existsById(id)) {
            throw new RuntimeException("Review with ID " + id + " does not exist.");
        }
        // Remove review from Customer's list before deletion
        Reviews review = reviewRepo.findById(id).get();
        review.getCustomer().getReviews().remove(review);
        reviewRepo.deleteById(id);
    }
}


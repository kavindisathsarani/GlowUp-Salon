package lk.ijse.backend.controller;

import lk.ijse.backend.dto.ReviewDTO;
import lk.ijse.backend.service.impl.ReviewServiceImpl;
import lk.ijse.backend.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/review")
@CrossOrigin(origins = "*")
public class ReviewController {
    @Autowired
    private ReviewServiceImpl reviewServiceImpl;

    @PostMapping(path = "save")
    public ResponseUtil saveReview(@RequestBody ReviewDTO reviewDTO) {
        reviewServiceImpl.save(reviewDTO);
        return new ResponseUtil(200, "Review is saved", null);
    }

    @GetMapping(path = "getAll")
    public ResponseUtil getAllReviews() {
        return new ResponseUtil(200, "Success", reviewServiceImpl.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseUtil> getReviewById(@PathVariable int id) {
        ReviewDTO reviewDTO = reviewServiceImpl.getById(id);
        return ResponseEntity.ok(new ResponseUtil(200, "Success", reviewDTO));
    }

    @PutMapping(path = "update")
    public ResponseUtil updateReview(@RequestBody ReviewDTO reviewDTO) {
        reviewServiceImpl.update(reviewDTO);
        return new ResponseUtil(200, "Review Updated Successfully", null);
    }

    @DeleteMapping(path = "delete/{id}")
    public ResponseUtil deleteReview(@PathVariable int id) {
        reviewServiceImpl.delete(id);
        return new ResponseUtil(200, "Review is deleted", null);
    }
}

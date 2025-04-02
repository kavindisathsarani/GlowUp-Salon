package lk.ijse.backend.controller;

import lk.ijse.backend.dto.UserReviewDTO;
import lk.ijse.backend.service.UserReviewService;
import lk.ijse.backend.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/userReview")
@CrossOrigin(origins = "*")
public class UserReviewController {
    @Autowired
    private UserReviewService userReviewService;

    @PostMapping("/save")
    public ResponseUtil saveUserReview(@RequestBody UserReviewDTO userReviewDTO) {
        userReviewService.save(userReviewDTO);
        return new ResponseUtil(200, "User review saved successfully", null);
    }

    @GetMapping("/getAll")
    public ResponseUtil getAllUserReviews() {
        return new ResponseUtil(200, "User reviews retrieved successfully", userReviewService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseUtil> getUserReviewById(@PathVariable int id) {
        UserReviewDTO userReviewDTO = userReviewService.getById(id);
        return ResponseEntity.ok(new ResponseUtil(200, "User review retrieved successfully", userReviewDTO));
    }

    @PutMapping("/update")
    public ResponseUtil updateUserReview(@RequestBody UserReviewDTO userReviewDTO) {
        userReviewService.update(userReviewDTO);
        return new ResponseUtil(200, "User review updated successfully", null);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseUtil deleteUserReview(@PathVariable int id) {
        userReviewService.delete(id);
        return new ResponseUtil(200, "User review deleted successfully", null);
    }
}

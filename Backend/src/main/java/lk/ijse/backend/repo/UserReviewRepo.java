package lk.ijse.backend.repo;

import lk.ijse.backend.entity.UserReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReviewRepo extends JpaRepository<UserReview, Integer> {
}

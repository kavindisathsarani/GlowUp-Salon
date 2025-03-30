package lk.ijse.backend.repo;

import lk.ijse.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserProfileRepo extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
}

package lk.ijse.backend.repo;


import lk.ijse.backend.entity.Customer;
import lk.ijse.backend.entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepo extends JpaRepository<Reviews, Integer> {
}

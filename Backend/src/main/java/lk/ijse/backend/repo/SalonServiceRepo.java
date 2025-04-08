package lk.ijse.backend.repo;


import lk.ijse.backend.entity.SalonService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SalonServiceRepo extends JpaRepository<SalonService, UUID> {
    SalonService findByName(String name);
}

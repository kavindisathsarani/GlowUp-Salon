package lk.ijse.backend.repo;


import lk.ijse.backend.entity.Packages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PackageRepo extends JpaRepository<Packages, UUID> {
    Packages findByName(String name);
}

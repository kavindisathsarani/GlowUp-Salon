package lk.ijse.backend.repo;

import lk.ijse.backend.entity.PackageAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageAppointmentRepo extends JpaRepository<PackageAppointment, Integer> {
}

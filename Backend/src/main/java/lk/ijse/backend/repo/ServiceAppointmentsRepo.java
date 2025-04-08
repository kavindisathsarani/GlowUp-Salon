package lk.ijse.backend.repo;

import lk.ijse.backend.entity.ServiceAppointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceAppointmentsRepo extends JpaRepository<ServiceAppointment, Integer> {

}

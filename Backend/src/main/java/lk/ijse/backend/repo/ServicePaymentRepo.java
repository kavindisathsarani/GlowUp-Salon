package lk.ijse.backend.repo;

import lk.ijse.backend.entity.ServicePayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServicePaymentRepo extends JpaRepository<ServicePayment, Integer> {
    Optional<ServicePayment> findByServiceAppointment_ServiceAppointmentId(int appointmentId);
}

package lk.ijse.backend.service;

import lk.ijse.backend.dto.serviceAppointmentDTO;

import java.util.List;

public interface ServiceAppointmentService {
    void save(serviceAppointmentDTO dto);
    List<serviceAppointmentDTO> getAll();
    serviceAppointmentDTO getById(int id);
    void update(serviceAppointmentDTO dto);
    void delete(int id);
}

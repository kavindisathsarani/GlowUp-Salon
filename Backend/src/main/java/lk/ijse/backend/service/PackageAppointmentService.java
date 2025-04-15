package lk.ijse.backend.service;

import lk.ijse.backend.dto.packageAppointmentDTO;

import java.util.List;

public interface PackageAppointmentService {
    void save(packageAppointmentDTO dto);
    List<packageAppointmentDTO> getAll();
    packageAppointmentDTO getById(int id);
    void update(packageAppointmentDTO dto);
    void delete(int id);
}

package lk.ijse.backend.service.impl;

import lk.ijse.backend.dto.serviceAppointmentDTO;
import lk.ijse.backend.entity.SalonService;
import lk.ijse.backend.entity.ServiceAppointment;
import lk.ijse.backend.entity.User;
import lk.ijse.backend.repo.SalonServiceRepo;
import lk.ijse.backend.repo.ServiceAppointmentsRepo;
import lk.ijse.backend.repo.UserRepository;
import lk.ijse.backend.service.ServiceAppointmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServiceAppointmentServiceImpl implements ServiceAppointmentService {
    @Autowired
    private ServiceAppointmentsRepo appointmentRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private SalonServiceRepo salonServiceRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void save(serviceAppointmentDTO dto) {
        ServiceAppointment appointment = new ServiceAppointment();

        User user = userRepo.findByEmail(dto.getUserEmail());
        if (user == null) {
            throw new RuntimeException("User not found with email: " + dto.getUserEmail());
        }

        SalonService service = salonServiceRepo.findByName(dto.getServiceName());
        if (service == null) {
            throw new RuntimeException("Service not found with name: " + dto.getServiceName());
        }

        appointment.setUser(user);
        appointment.setService(service);
        appointment.setBookingDate(dto.getBookingDate());
        appointment.setAppointmentTime(dto.getAppointmentTime());
        appointment.setStatus(dto.getStatus());

        appointmentRepo.save(appointment);
    }

    @Override
    public List<serviceAppointmentDTO> getAll() {
        return appointmentRepo.findAll().stream().map(appointment -> {
            serviceAppointmentDTO dto = modelMapper.map(appointment, serviceAppointmentDTO.class);
            dto.setUserEmail(appointment.getUser().getEmail());
            dto.setServiceName(appointment.getService().getName());
            dto.setPrice(appointment.getService().getPrice());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public serviceAppointmentDTO getById(int id) {
        Optional<ServiceAppointment> optional = appointmentRepo.findById(id);
        if (optional.isPresent()) {
            ServiceAppointment appointment = optional.get();
            serviceAppointmentDTO dto = modelMapper.map(appointment, serviceAppointmentDTO.class);
            dto.setUserEmail(appointment.getUser().getEmail());
            dto.setServiceName(appointment.getService().getName());
            dto.setPrice(appointment.getService().getPrice());
            return dto;
        }
        throw new RuntimeException("Appointment not found with ID: " + id);
    }

    @Override
    public void update(serviceAppointmentDTO dto) {
        Optional<ServiceAppointment> optional = appointmentRepo.findById(dto.getServiceAppointmentId());
        if (!optional.isPresent()) {
            throw new RuntimeException("Appointment does not exist with ID: " + dto.getServiceAppointmentId());
        }

        ServiceAppointment appointment = optional.get();

        // Check and update user
        if (!appointment.getUser().getEmail().equals(dto.getUserEmail())) {
            User user = userRepo.findByEmail(dto.getUserEmail());
            if (user == null) {
                throw new RuntimeException("User not found with email: " + dto.getUserEmail());
            }
            appointment.setUser(user);
        }

        // Check and update service
        if (!appointment.getService().getName().equals(dto.getServiceName())) {
            SalonService service = salonServiceRepo.findByName(dto.getServiceName());
            if (service == null) {
                throw new RuntimeException("Service not found with name: " + dto.getServiceName());
            }
            appointment.setService(service);
        }

        appointment.setBookingDate(dto.getBookingDate());
        appointment.setAppointmentTime(dto.getAppointmentTime());
        appointment.setStatus(dto.getStatus());

        appointmentRepo.save(appointment);
    }

    @Override
    public void delete(int id) {
        if (!appointmentRepo.existsById(id)) {
            throw new RuntimeException("Appointment does not exist with ID: " + id);
        }
        appointmentRepo.deleteById(id);
    }
}

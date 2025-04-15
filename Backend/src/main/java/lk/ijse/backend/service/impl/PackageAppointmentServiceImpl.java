package lk.ijse.backend.service.impl;

import lk.ijse.backend.dto.packageAppointmentDTO;
import lk.ijse.backend.entity.PackageAppointment;
import lk.ijse.backend.entity.Packages;
import lk.ijse.backend.entity.User;
import lk.ijse.backend.repo.PackageAppointmentRepo;
import lk.ijse.backend.repo.PackageRepo;
import lk.ijse.backend.repo.UserRepository;
import lk.ijse.backend.service.PackageAppointmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PackageAppointmentServiceImpl implements PackageAppointmentService {
    @Autowired
    private PackageAppointmentRepo packageAppointmentRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PackageRepo packageRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void save(packageAppointmentDTO dto) {
        PackageAppointment packageAppointment = new PackageAppointment();

        User user = userRepo.findByEmail(dto.getUserEmail());
        if (user == null) {
            throw new RuntimeException("User not found with email: " + dto.getUserEmail());
        }

        Packages aPackage = packageRepo.findByName(dto.getPackageName());
        if (aPackage == null) {
            throw new RuntimeException("Package not found with name: " + dto.getPackageName());
        }

        packageAppointment.setUser(user);
        packageAppointment.setaPackage(aPackage);
        packageAppointment.setBookingDate(dto.getBookingDate());
        packageAppointment.setAppointmentTime(dto.getAppointmentTime());
        packageAppointment.setStatus(dto.getStatus());
        packageAppointment.setNotes(dto.getNotes());

        packageAppointmentRepo.save(packageAppointment);
    }

    @Override
    public List<packageAppointmentDTO> getAll() {
        return packageAppointmentRepo.findAll().stream().map(appointment -> {
            packageAppointmentDTO dto = modelMapper.map(appointment, packageAppointmentDTO.class);
            dto.setUserEmail(appointment.getUser().getEmail());
            dto.setPackageName(appointment.getaPackage().getName());
            dto.setPrice(appointment.getaPackage().getTotalPrice());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public packageAppointmentDTO getById(int id) {
        Optional<PackageAppointment> optional = packageAppointmentRepo.findById(id);
        if (optional.isPresent()) {
            PackageAppointment appointment = optional.get();
            packageAppointmentDTO dto = modelMapper.map(appointment, packageAppointmentDTO.class);
            dto.setUserEmail(appointment.getUser().getEmail());
            dto.setPackageName(appointment.getaPackage().getName());
            dto.setPrice(appointment.getaPackage().getTotalPrice());
            return dto;
        }
        throw new RuntimeException("Appointment not found with ID: " + id);
    }

    @Override
    public void update(packageAppointmentDTO dto) {
        Optional<PackageAppointment> optional = packageAppointmentRepo.findById(dto.getPackageAppointmentId());
        if (!optional.isPresent()) {
            throw new RuntimeException("Appointment does not exist with ID: " + dto.getPackageAppointmentId());
        }

        PackageAppointment appointment = optional.get();

        if (!appointment.getUser().getEmail().equals(dto.getUserEmail())) {
            User user = userRepo.findByEmail(dto.getUserEmail());
            if (user == null) {
                throw new RuntimeException("User not found with email: " + dto.getUserEmail());
            }
            appointment.setUser(user);
        }

        if (!appointment.getaPackage().getName().equals(dto.getPackageName())) {
            Packages aPackage = packageRepo.findByName(dto.getPackageName());
            if (aPackage == null) {
                throw new RuntimeException("Package not found with name: " + dto.getPackageName());
            }
            appointment.setaPackage(aPackage);
        }

        appointment.setBookingDate(dto.getBookingDate());
        appointment.setAppointmentTime(dto.getAppointmentTime());
        appointment.setStatus(dto.getStatus());
        appointment.setNotes(dto.getNotes());

        packageAppointmentRepo.save(appointment);
    }

    @Override
    public void delete(int id) {
        if (!packageAppointmentRepo.existsById(id)) {
            throw new RuntimeException("Appointment does not exist with ID: " + id);
        }
        packageAppointmentRepo.deleteById(id);
    }
}

package lk.ijse.backend.service.impl;

import lk.ijse.backend.dto.UserDTO;
import lk.ijse.backend.entity.User;
import lk.ijse.backend.repo.UserProfileRepo;
import lk.ijse.backend.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private UserProfileRepo userProfileRepository;

    @Override
    public UserDTO getUserProfileByEmail(String email) {
        User user = userProfileRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return convertToDTO(user);
    }

    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setRole(user.getRole());
        // Don't set password for security reasons
        return userDTO;
    }

}

package lk.ijse.backend.controller;

import lk.ijse.backend.dto.UserDTO;
import lk.ijse.backend.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/profile")
@CrossOrigin(origins = "*")
public class UserProfileController {
    @Autowired
    private ProfileService userProfileService;

    @GetMapping
    public ResponseEntity<UserDTO> getCurrentUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // Assuming email is used as the username

        UserDTO userDTO = userProfileService.getUserProfileByEmail(email);

        return ResponseEntity.ok(userDTO);
    }
}

package lk.ijse.backend.service;

import lk.ijse.backend.dto.UserDTO;

public interface ProfileService {
    UserDTO getUserProfileByEmail(String email);
}

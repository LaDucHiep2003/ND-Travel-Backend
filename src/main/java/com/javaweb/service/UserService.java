package com.javaweb.service;

import com.javaweb.model.TourResponse;
import com.javaweb.model.UserDTO;
import java.util.List;

public interface UserService {
    List<UserDTO> findAll();
    UserDTO findById(Long id);
    TourResponse createUser(UserDTO userDTO);
    TourResponse updateUser(UserDTO userDTO);
    TourResponse deleteUser(List<Long> ids);
} 
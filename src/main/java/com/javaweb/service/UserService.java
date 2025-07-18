package com.javaweb.service;

import com.javaweb.model.TourResponse;
import com.javaweb.model.UserDTO;
import java.util.List;
import java.util.Map;

public interface UserService {
    List<UserDTO> findAll(Map<String, Object> params);
    UserDTO findById(Long id);
    TourResponse createUser(UserDTO userDTO);
    TourResponse updateUser(UserDTO userDTO);
    TourResponse deleteUser(List<Long> ids);
} 
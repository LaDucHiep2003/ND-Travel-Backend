package com.javaweb.service;

import com.javaweb.model.TourResponse;
import com.javaweb.model.UserDTO;
import com.javaweb.model.ApiResponse;
import com.javaweb.model.request.UserRequest;
import com.javaweb.model.response.UserResponse;
import com.javaweb.repository.UserRepository;
import com.javaweb.repository.entity.UserEntity;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<UserDTO> findAll(Map<String, Object> params);
    UserResponse findById(Long id);
    UserResponse createUser(UserRequest userRequest);
    UserResponse updateUser(UserRequest userRequest);
    TourResponse deleteUser(List<Long> ids);
} 
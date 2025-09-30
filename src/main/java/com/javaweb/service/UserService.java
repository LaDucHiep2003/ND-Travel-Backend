package com.javaweb.service;

import com.javaweb.model.TourResponse;
import com.javaweb.model.request.UserRequest;
import com.javaweb.model.response.UserResponse;


import java.util.List;
import java.util.Map;

public interface UserService {
    List<UserResponse> findAll(Map<String, Object> params);
    UserResponse findById(Long id);
    UserResponse getMyInfo();
    UserResponse createUser(UserRequest userRequest);
    UserResponse updateUser(UserRequest userRequest);
    void deleteUser(List<Long> ids);
} 
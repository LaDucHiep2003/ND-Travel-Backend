package com.javaweb.service;

import com.javaweb.model.response.AuthResponse;

public interface AuthService {
    AuthResponse login(String username, String password);
}

package com.javaweb.service;

import com.javaweb.model.request.AuthenticationRequest;
import com.javaweb.model.response.AuthenticationResponse;

public interface AuthService {
    boolean login(AuthenticationRequest authenticationRequest);
}

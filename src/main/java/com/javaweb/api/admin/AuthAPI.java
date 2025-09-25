package com.javaweb.api.admin;


import com.javaweb.model.ApiResponse;
import com.javaweb.model.request.AuthenticationRequest;
import com.javaweb.model.request.IntrospectRequest;
import com.javaweb.model.response.AuthenticationResponse;
import com.javaweb.model.response.IntrospectResponse;
import com.javaweb.service.AuthService;
import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthAPI {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ApiResponse<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
       var result = authService.login(request);
       return ApiResponse.<AuthenticationResponse>builder()
               .result(result)
               .build();
    }

    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        var result = authService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder()
                .result(result)
                .build();
    }
}

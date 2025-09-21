package com.javaweb.api.admin;


import com.javaweb.model.ApiResponse;
import com.javaweb.model.request.AuthenticationRequest;
import com.javaweb.model.response.AuthenticationResponse;
import com.javaweb.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AuthAPI {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ApiResponse<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
       boolean result = authService.login(request);
       return ApiResponse.<AuthenticationResponse>builder()
               .result(AuthenticationResponse.builder()
                       .authenticated(result).build())
               .build();
    }
}

package com.javaweb.api;


import com.javaweb.model.request.AuthRequest;
import com.javaweb.model.response.AuthResponse;
import com.javaweb.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class AuthAPI {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        AuthResponse msg = authService.login(request.getUsername(), request.getPassword());
        return ResponseEntity.status(200).body(msg);
    }
}

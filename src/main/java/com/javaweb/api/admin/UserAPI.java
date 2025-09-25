package com.javaweb.api.admin;

import com.javaweb.model.TourResponse;
import com.javaweb.model.ApiResponse;
import com.javaweb.model.request.UserRequest;
import com.javaweb.model.response.UserResponse;
import com.javaweb.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class UserAPI {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<UserResponse> findAll(@RequestParam Map<String, Object> params) {
        return userService.findAll(params);
    }

    @GetMapping("/users/{id}")
    public UserResponse findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping("/users")
    public ApiResponse<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        UserResponse result = userService.createUser(userRequest);
        apiResponse.setResult(result);
        return apiResponse;
    }

    @PutMapping("/users")
    public ApiResponse<UserResponse> updateUser(@RequestBody UserRequest userRequest) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        UserResponse result = userService.updateUser(userRequest);
        apiResponse.setResult(result);
        return apiResponse;
    }
    @DeleteMapping("/users")
    public ResponseEntity<TourResponse> deleteUser(@RequestParam List<Long> ids) {
        TourResponse msg = userService.deleteUser(ids);
        return ResponseEntity.ok(msg);
    }
} 
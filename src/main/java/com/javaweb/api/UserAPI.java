package com.javaweb.api;

import com.google.protobuf.Api;
import com.javaweb.model.TourResponse;
import com.javaweb.model.ApiResponse;
import com.javaweb.model.request.UserRequest;
import com.javaweb.model.response.UserResponse;
import com.javaweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/users")
public class UserAPI {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public ApiResponse<List<UserResponse>> findAll(@RequestParam Map<String, Object> params) {
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.findAll(params))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> findById(@PathVariable Long id) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.findById(id))
                .build();
    }

    @GetMapping("/myInfo")
    public ApiResponse<UserResponse> myInfo(){
        return ApiResponse.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }

    @PostMapping("")
    public ApiResponse<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        UserResponse result = userService.createUser(userRequest);
        apiResponse.setResult(result);
        return apiResponse;
    }

    @PutMapping("")
    public ApiResponse<UserResponse> updateUser(@RequestBody UserRequest userRequest) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        UserResponse result = userService.updateUser(userRequest);
        apiResponse.setResult(result);
        return apiResponse;
    }
    @DeleteMapping("")
    public ApiResponse<String> deleteUser(@RequestParam List<Long> ids) {
        userService.deleteUser(ids);
        return ApiResponse.<String>builder()
                .result("User has been deleted")
                .build();
    }
} 
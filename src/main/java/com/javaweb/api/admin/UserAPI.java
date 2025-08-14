package com.javaweb.api.admin;

import com.javaweb.model.TourResponse;
import com.javaweb.model.UserDTO;
import com.javaweb.model.response.ApiResponse;
import com.javaweb.repository.entity.UserEntity;
import com.javaweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/admin")
public class UserAPI {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<UserDTO> findAll(@RequestParam Map<String, Object> params) {
        return userService.findAll(params);
    }

    @GetMapping("/users/{id}")
    public UserDTO findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<ApiResponse<UserEntity>> createUser(@RequestBody UserDTO userDTO) {
        ApiResponse<UserEntity> response = userService.createUser(userDTO);

        // Nếu status là 201 (tạo thành công)
        if (response.getStatus() == 201) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }

        // Nếu status là 409 (xung đột), hoặc lỗi khác (tùy bạn xử lý)
        return ResponseEntity.status(HttpStatus.valueOf(response.getStatus())).body(response);
    }

    @PatchMapping("/users")
    public ResponseEntity<TourResponse> updateUser(@RequestBody UserDTO userDTO) {
        TourResponse msg = userService.updateUser(userDTO);
        return ResponseEntity.ok(msg);
    }
    @DeleteMapping("/users")
    public ResponseEntity<TourResponse> deleteUser(@RequestParam List<Long> ids) {
        TourResponse msg = userService.deleteUser(ids);
        return ResponseEntity.ok(msg);
    }
} 
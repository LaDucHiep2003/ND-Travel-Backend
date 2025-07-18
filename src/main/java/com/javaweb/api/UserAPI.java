package com.javaweb.api;

import com.javaweb.model.TourResponse;
import com.javaweb.model.UserDTO;
import com.javaweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class UserAPI {
    @Autowired
    private UserService userService;

    @GetMapping("/api/users")
    public List<UserDTO> findAll(@RequestParam Map<String, Object> params) {
        return userService.findAll(params);
    }

    @GetMapping("/api/users/{id}")
    public UserDTO findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping("/api/users")
    public ResponseEntity<TourResponse> createUser(@RequestBody UserDTO userDTO) {
        TourResponse msg = userService.createUser(userDTO);
        return ResponseEntity.ok(msg);
    }

    @PatchMapping("/api/users")
    public ResponseEntity<TourResponse> updateUser(@RequestBody UserDTO userDTO) {
        TourResponse msg = userService.updateUser(userDTO);
        return ResponseEntity.ok(msg);
    }
    @DeleteMapping("/api/users")
    public ResponseEntity<TourResponse> deleteUser(@RequestParam List<Long> ids) {
        TourResponse msg = userService.deleteUser(ids);
        return ResponseEntity.ok(msg);
    }
} 
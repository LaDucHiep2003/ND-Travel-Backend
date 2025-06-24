package com.javaweb.api;

import com.javaweb.model.TourResponse;
import com.javaweb.model.UserDTO;
import com.javaweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class UserAPI {
    @Autowired
    private UserService userService;

    @GetMapping("/api/users")
    public List<UserDTO> findAll() {
        return userService.findAll();
    }

    @GetMapping("/api/users/{id}")
    public UserDTO findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping("/api/users")
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @PatchMapping("/api/users")
    public UserDTO updateUser(@RequestBody UserDTO userDTO) {
        return userService.updateUser(userDTO);
    }
    @DeleteMapping("/api/users")
    public ResponseEntity<TourResponse> deleteUser(@RequestParam List<Long> ids) {
        TourResponse msg = userService.deleteUser(ids);
        return ResponseEntity.ok(msg);
    }
} 
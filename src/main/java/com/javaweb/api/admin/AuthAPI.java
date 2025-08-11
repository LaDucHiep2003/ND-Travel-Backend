package com.javaweb.api.admin;


import com.javaweb.model.TourCategoryDTO;
import com.javaweb.model.TourResponse;
import com.javaweb.model.request.AuthRequest;
import com.javaweb.model.response.AuthResponse;
import com.javaweb.service.AuthService;
import com.javaweb.service.TourCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class AuthAPI {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        AuthResponse response = authService.login(request.getUsername(), request.getPassword());
        if(response.getStatus() == 200){
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        return ResponseEntity.status(HttpStatus.valueOf(response.getStatus())).body(response);
    }

    @RestController
    @CrossOrigin(origins = "http://localhost:5173")
    public static class TourCategoryAPI {
        @Autowired
        private TourCategoryService tourCategoryService;

        @GetMapping("/api/category")
        public List<TourCategoryDTO> findAll(){
            return tourCategoryService.findAll();
        }
        @GetMapping("/api/category/{id}")
        public TourCategoryDTO findById(@PathVariable Long id){
            return tourCategoryService.findById(id);
        }

        @PostMapping("/api/category")
        public ResponseEntity<TourResponse> createCategory(@RequestBody TourCategoryDTO tourCategoryDTO){
            TourResponse result = tourCategoryService.createCategory(tourCategoryDTO);
            return ResponseEntity.ok(result);
        }
        @PatchMapping("/api/category")
        public ResponseEntity<TourResponse> updateCategory(@RequestBody TourCategoryDTO tourCategoryDTO){
            TourResponse result = tourCategoryService.updateCategory(tourCategoryDTO);
            return ResponseEntity.ok(result);
        }
        @DeleteMapping("/api/category")
        public ResponseEntity<TourResponse> save(@RequestParam List<Long> ids){
            TourResponse result = tourCategoryService.deleteCategory(ids);
            return ResponseEntity.ok(result);
        }
    }
}

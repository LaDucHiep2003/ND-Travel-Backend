package com.javaweb.api.admin;

import com.javaweb.model.TourCategoryDTO;
import com.javaweb.model.TourResponse;
import com.javaweb.service.TourCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class TourCategoryAPI {
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

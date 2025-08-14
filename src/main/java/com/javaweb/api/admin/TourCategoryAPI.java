package com.javaweb.api.admin;

import com.javaweb.model.TourCategoryDTO;
import com.javaweb.model.TourResponse;
import com.javaweb.service.TourCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/admin")
public class TourCategoryAPI {
    @Autowired
    private TourCategoryService tourCategoryService;

    @GetMapping("/category")
    public List<TourCategoryDTO> findAll(@RequestParam Map<String, Object> params){
        return tourCategoryService.findAll(params);
    }
    @GetMapping("/category/{id}")
    public TourCategoryDTO findById(@PathVariable Long id){
        return tourCategoryService.findById(id);
    }

    @PostMapping("/category")
    public ResponseEntity<TourResponse> createCategory(@RequestBody TourCategoryDTO tourCategoryDTO){
        TourResponse result = tourCategoryService.createCategory(tourCategoryDTO);
        return ResponseEntity.ok(result);
    }
    @PatchMapping("/category")
    public ResponseEntity<TourResponse> updateCategory(@RequestBody TourCategoryDTO tourCategoryDTO){
        TourResponse result = tourCategoryService.updateCategory(tourCategoryDTO);
        return ResponseEntity.ok(result);
    }
    @DeleteMapping("/category")
    public ResponseEntity<TourResponse> save(@RequestParam List<Long> ids){
        TourResponse result = tourCategoryService.deleteCategory(ids);
        return ResponseEntity.ok(result);
    }
}

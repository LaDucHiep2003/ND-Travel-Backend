package com.javaweb.api.admin;

import com.javaweb.model.ApiResponse;
import com.javaweb.model.request.TourCategoryRequest;
import com.javaweb.model.response.TourCategoryResponse;
import com.javaweb.service.TourCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<TourCategoryResponse> findAll(@RequestParam Map<String, Object> params){
        return tourCategoryService.findAll(params);
    }

    @GetMapping("/category/{id}")
    public TourCategoryResponse findById(@PathVariable Long id){
        return tourCategoryService.findById(id);
    }

    @PostMapping("/category")
    public ApiResponse<TourCategoryResponse> createCategory(@RequestBody TourCategoryRequest tourCategoryDTO){
        ApiResponse<TourCategoryResponse> apiResponse = new ApiResponse<>();
        TourCategoryResponse result = tourCategoryService.createCategory(tourCategoryDTO);
        apiResponse.setResult(result);
        return apiResponse;
    }
    @PutMapping("/category")
    public ApiResponse<TourCategoryResponse> updateCategory(@RequestBody TourCategoryRequest tourCategoryDTO){
        ApiResponse<TourCategoryResponse> apiResponse = new ApiResponse<>();
        TourCategoryResponse result = tourCategoryService.updateCategory(tourCategoryDTO);
        apiResponse.setResult(result);
        return apiResponse;
    }
    @DeleteMapping("/category")
    public String save(@RequestParam List<Long> ids){
        tourCategoryService.deleteCategory(ids);
        return "Category has been deleted";
    }
}

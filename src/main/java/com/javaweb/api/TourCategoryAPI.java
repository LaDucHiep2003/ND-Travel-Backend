package com.javaweb.api;

import com.google.protobuf.Api;
import com.javaweb.model.ApiResponse;
import com.javaweb.model.request.TourCategoryRequest;
import com.javaweb.model.response.TourCategoryResponse;
import com.javaweb.model.response.TourResponse;
import com.javaweb.service.TourCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class TourCategoryAPI {
    @Autowired
    private TourCategoryService tourCategoryService;

    @GetMapping("/category")
    public ApiResponse<List<TourCategoryResponse>> findAll(@RequestParam Map<String, Object> params){
        return ApiResponse.<List<TourCategoryResponse>>builder()
                .result(tourCategoryService.findAll(params)).build();
    }

    @GetMapping("/category/{id}")
    public ApiResponse<TourCategoryResponse> findById(@PathVariable Long id){
        return ApiResponse.<TourCategoryResponse>builder()
                .result(tourCategoryService.findById(id)).build();
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
    public ApiResponse<String> save(@RequestParam List<Long> ids){
        tourCategoryService.deleteCategory(ids);
        return ApiResponse.<String>builder()
                .result("Category has been deleted").build();
    }
}

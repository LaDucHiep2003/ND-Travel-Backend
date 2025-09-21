package com.javaweb.service;

import com.javaweb.model.TourCategoryDTO;
import com.javaweb.model.TourResponse;
import com.javaweb.model.request.TourCategoryRequest;
import com.javaweb.model.response.TourCategoryResponse;

import java.util.List;
import java.util.Map;

public interface TourCategoryService {
    List<TourCategoryResponse> findAll(Map<String, Object> params);
    TourCategoryResponse findById(Long id);
    TourCategoryResponse createCategory(TourCategoryRequest tourCategoryDTO);
    TourCategoryResponse updateCategory(TourCategoryRequest tourCategoryDTO);
    void deleteCategory(List<Long> ids);
}

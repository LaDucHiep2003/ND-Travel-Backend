package com.javaweb.service;

import com.javaweb.model.TourCategoryDTO;
import com.javaweb.model.TourResponse;

import java.util.List;

public interface TourCategoryService {
    List<TourCategoryDTO> findAll();
    TourCategoryDTO findById(Long id);
    TourResponse createCategory(TourCategoryDTO tourCategoryDTO);
    TourResponse updateCategory(TourCategoryDTO tourCategoryDTO);
    TourResponse deleteCategory(List<Long> ids);
}

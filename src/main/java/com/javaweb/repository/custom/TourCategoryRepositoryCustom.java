package com.javaweb.repository.custom;

import com.javaweb.builder.TourCategorySearchBuilder;
import com.javaweb.repository.entity.TourCategoryEntity;

import java.util.List;

public interface TourCategoryRepositoryCustom {
    List<TourCategoryEntity> findAll(TourCategorySearchBuilder tourCategorySearchBuilder);
    void deleteCategory(List<Long> ids);
}

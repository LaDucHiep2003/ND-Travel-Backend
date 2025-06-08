package com.javaweb.repository.custom.Impl;

import com.javaweb.repository.custom.TourCategoryRepositoryCustom;
import com.javaweb.repository.entity.TourCategoryEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class TourCategoryRepositoryImpl implements TourCategoryRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void deleteCategory(List<Long> ids) {
        for(Long id : ids) {
            TourCategoryEntity existingEntity = entityManager.find(TourCategoryEntity.class, id);
            if (existingEntity != null) {
                existingEntity.setDeleted(true);
            }
        }
    }
}

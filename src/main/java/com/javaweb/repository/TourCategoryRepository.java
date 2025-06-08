package com.javaweb.repository;

import com.javaweb.repository.custom.TourCategoryRepositoryCustom;
import com.javaweb.repository.entity.TourCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourCategoryRepository extends JpaRepository<TourCategoryEntity, Long>, TourCategoryRepositoryCustom {
    List<TourCategoryEntity> findByDeletedFalse();
}

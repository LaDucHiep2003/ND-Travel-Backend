package com.javaweb.repository;

import com.javaweb.model.TourDTO;
import com.javaweb.repository.custom.TourRepositoryCustom;
import com.javaweb.repository.entity.TourEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourRepository extends JpaRepository<TourEntity, Long>, TourRepositoryCustom {
    void deleteByIdIn(List<Long> ids);
}

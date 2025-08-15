package com.javaweb.repository.custom;

import com.javaweb.builder.TourSearchBuilder;
import com.javaweb.repository.entity.TourEntity;

import java.util.List;

public interface TourRepositoryCustom {
    List<TourEntity> findAll(TourSearchBuilder tourSearchBuilder);
    long countAll(TourSearchBuilder tourSearchBuilder);
    void deleteTour(List<Long> ids);
}

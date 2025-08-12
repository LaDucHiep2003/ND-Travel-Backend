package com.javaweb.repository.custom;

import com.javaweb.repository.entity.TourEntity;
import com.javaweb.repository.entity.TourItineraryEntity;

import java.util.List;

public interface TourItineraryRepositoryCustom {
    List<TourItineraryEntity> findByTourOrderByDayNumberAscOrderIndexAsc(TourEntity tour);
}

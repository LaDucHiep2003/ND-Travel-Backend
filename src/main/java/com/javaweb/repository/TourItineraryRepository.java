package com.javaweb.repository;

import com.javaweb.repository.entity.TourItineraryEntity;
import com.javaweb.repository.entity.TourEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourItineraryRepository extends JpaRepository<TourItineraryEntity, Long> {
    List<TourItineraryEntity> findByTourOrderByDayNumberAscOrderIndexAsc(TourEntity tour);
}



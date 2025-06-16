package com.javaweb.service;

import com.javaweb.model.TourDTO;
import com.javaweb.model.TourResponse;
import com.javaweb.repository.entity.TourEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TourService {
    List<TourDTO> findAll(Map<String, Object> params);
    TourDTO findById(Long id);
    TourResponse createTour(TourDTO tourDTO);
    TourResponse editTour(TourDTO tourDTO);
    TourResponse deleteTour(List<Long> ids);
}

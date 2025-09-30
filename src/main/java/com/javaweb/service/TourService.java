package com.javaweb.service;

import com.javaweb.model.request.TourRequest;
import com.javaweb.model.response.TourResponse;

import java.util.List;
import java.util.Map;

public interface TourService {
    List<TourResponse> findAll(Map<String, Object> params);
    long count(Map<String, Object> params);
    TourResponse findById(Long id);
    TourResponse createTour(TourRequest tourDTO);
    TourResponse editTour(TourRequest tourDTO);
    void deleteTour(List<Long> ids);
}

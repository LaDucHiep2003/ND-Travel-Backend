package com.javaweb.service;

import java.util.List;

import com.javaweb.model.TourItineraryDTO;
import com.javaweb.repository.entity.ItineraryStatus;

public interface TourItineraryService {
    List<TourItineraryDTO> getItinerariesByTourId(Long tourId);
    TourItineraryDTO updateItineraryStatus(Long itineraryId, ItineraryStatus status);
}

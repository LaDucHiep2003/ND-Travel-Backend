package com.javaweb.service.impl;

import com.javaweb.converter.TourItineraryDTOConverter;
import com.javaweb.model.TourItineraryDTO;
import com.javaweb.repository.TourItineraryRepository;
import com.javaweb.repository.TourRepository;
import com.javaweb.repository.entity.TourEntity;
import com.javaweb.repository.entity.TourItineraryEntity;
import com.javaweb.repository.entity.ItineraryStatus;
import com.javaweb.service.TourItineraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TourItineraryServiceImpl implements TourItineraryService {

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private TourItineraryRepository tourItineraryRepository;

    @Autowired
    private TourItineraryDTOConverter tourItineraryDTOConverter;

    @Override
    public List<TourItineraryDTO> getItinerariesByTourId(Long tourId) {
        TourEntity tour = tourRepository.findById(tourId).orElse(null);
        if (tour == null) {
            return List.of();
        }
        List<TourItineraryEntity> items = tourItineraryRepository.findByTourOrderByDayNumberAscOrderIndexAsc(tour);
        return items.stream().map(tourItineraryDTOConverter::toDTO).collect(Collectors.toList());
    }

    @Override
    public TourItineraryDTO updateItineraryStatus(Long itineraryId, ItineraryStatus status) {
        TourItineraryEntity entity = tourItineraryRepository.findById(itineraryId).orElse(null);
        if (entity == null) {
            return null;
        }
        entity.setStatus(status);
        TourItineraryEntity savedEntity = tourItineraryRepository.save(entity);
        return tourItineraryDTOConverter.toDTO(savedEntity);
    }
}



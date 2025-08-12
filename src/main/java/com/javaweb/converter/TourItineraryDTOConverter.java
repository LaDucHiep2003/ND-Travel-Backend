package com.javaweb.converter;

import com.javaweb.model.TourItineraryDTO;
import com.javaweb.repository.entity.TourItineraryEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TourItineraryDTOConverter {

    @Autowired
    private ModelMapper modelMapper;

    public TourItineraryDTO toDTO(TourItineraryEntity entity) {
        TourItineraryDTO dto = modelMapper.map(entity, TourItineraryDTO.class);
        if (entity.getTour() != null) {
            dto.setTourId(entity.getTour().getId());
        }
        return dto;
    }
}



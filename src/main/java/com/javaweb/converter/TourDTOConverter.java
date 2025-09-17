package com.javaweb.converter;

import com.javaweb.model.TourDTO;
import com.javaweb.model.response.TourResponse;
import com.javaweb.repository.entity.TourEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TourDTOConverter {

    @Autowired
    private ModelMapper modelMapper;


    public TourResponse toTourDTO(TourEntity item) {
        return modelMapper.map(item, TourResponse.class);
    }
}

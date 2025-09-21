package com.javaweb.converter;

import com.javaweb.model.TourCategoryDTO;
import com.javaweb.model.response.TourCategoryResponse;
import com.javaweb.repository.entity.TourCategoryEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TourCategoryDTOConverter {

    @Autowired
    private ModelMapper modelMapper;

    public TourCategoryResponse tourCategoryDTO(TourCategoryEntity item){
        return modelMapper.map(item, TourCategoryResponse.class);
    }
}

package com.javaweb.service.impl;

import com.javaweb.builder.TourCategorySearchBuilder;
import com.javaweb.converter.TourCategoryDTOConverter;
import com.javaweb.converter.TourCategorySearchBuilderConverter;
import com.javaweb.model.TourCategoryDTO;
import com.javaweb.model.TourResponse;
import com.javaweb.model.request.TourCategoryRequest;
import com.javaweb.model.response.TourCategoryResponse;
import com.javaweb.repository.TourCategoryRepository;
import com.javaweb.repository.entity.TourCategoryEntity;
import com.javaweb.service.TourCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TourCategoryServiceImpl implements TourCategoryService {
    @Autowired
    private TourCategoryRepository tourCategoryRepository;

    @Autowired
    private TourCategoryDTOConverter tourCategoryDTOConverter;

    @Autowired
    private TourCategorySearchBuilderConverter tourCategorySearchBuilderConverter;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<TourCategoryResponse> findAll(Map<String, Object> params) {
        TourCategorySearchBuilder tourCategorySearchBuilder = tourCategorySearchBuilderConverter.tourCategorySearchBuilder(params);
        List<TourCategoryEntity> tourCategoryEntities = tourCategoryRepository.findAll(tourCategorySearchBuilder);
        List<TourCategoryResponse> result = new ArrayList<>();
        for (TourCategoryEntity tourCategoryEntity : tourCategoryEntities) {
            TourCategoryResponse tourCategoryDTO = tourCategoryDTOConverter.tourCategoryDTO(tourCategoryEntity);
            result.add(tourCategoryDTO);
        }
        return result;
    }

    @Override
    public TourCategoryResponse findById(Long id) {
        TourCategoryEntity tourCategoryEntities = tourCategoryRepository.findById(id).get();
        TourCategoryResponse tourCategoryDTO = tourCategoryDTOConverter.tourCategoryDTO(tourCategoryEntities);
        return tourCategoryDTO;
    }

    @Override
    public TourCategoryResponse createCategory(TourCategoryRequest tourCategoryDTO) {
        TourCategoryEntity tourCategoryEntity = new TourCategoryEntity();
        modelMapper.map(tourCategoryDTO, tourCategoryEntity);
        TourCategoryEntity result = tourCategoryRepository.save(tourCategoryEntity);
        return tourCategoryDTOConverter.tourCategoryDTO(result);
    }

    @Override
    public TourCategoryResponse updateCategory(TourCategoryRequest tourCategoryDTO) {
        TourCategoryEntity tourCategoryEntity = tourCategoryRepository.findById(tourCategoryDTO.getId()).get();
        modelMapper.map(tourCategoryDTO, tourCategoryEntity);
        TourCategoryEntity result = tourCategoryRepository.save(tourCategoryEntity);
        return tourCategoryDTOConverter.tourCategoryDTO(result);
    }

    @Transactional
    @Override
    public void deleteCategory(List<Long> ids) {
        tourCategoryRepository.deleteCategory(ids);
    }
}

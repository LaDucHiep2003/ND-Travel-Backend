package com.javaweb.service.impl;

import com.javaweb.builder.TourSearchBuilder;
import com.javaweb.converter.TourDTOConverter;
import com.javaweb.converter.TourSearchBuilderConverter;
import com.javaweb.model.TourDTO;
import com.javaweb.model.request.TourRequest;
import com.javaweb.model.response.TourResponse;
import com.javaweb.repository.TourCategoryRepository;
import com.javaweb.repository.TourRepository;
import com.javaweb.repository.entity.TourCategoryEntity;
import com.javaweb.repository.entity.TourEntity;
import com.javaweb.service.TourService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TourServiceImpl implements TourService {
    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private TourDTOConverter tourDTOConverter;

    @Autowired
    private TourSearchBuilderConverter tourSearchBuilderConverter;

    @Autowired
    private TourCategoryRepository tourCategoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<TourResponse> findAll(Map<String, Object> params) {
        TourSearchBuilder tourSearchBuilders = tourSearchBuilderConverter.toTourSearchBuilder(params);
        List<TourEntity> tourEntities = tourRepository.findAll(tourSearchBuilders);
        List<TourResponse> result = new ArrayList<>();
        for(TourEntity item : tourEntities){
            TourResponse tourDTO = tourDTOConverter.toTourDTO(item);
            result.add(tourDTO);
        }
        return result;
    }

    @Override
    public long count(Map<String, Object> params) {
        TourSearchBuilder tourSearchBuilders = tourSearchBuilderConverter.toTourSearchBuilder(params);
        return tourRepository.countAll(tourSearchBuilders);
    }

    @Override
    public TourResponse findById(Long id) {
        TourEntity tourEntity = tourRepository.findById(id).get();
        TourResponse tourDTO = tourDTOConverter.toTourDTO(tourEntity);
//        List<Long> categoryIds = tourEntity.getTourCategories().stream()
//                .map(TourCategoryEntity::getId)
//                .collect(Collectors.toList());
//        tourDTO.setCategory_id(categoryIds);
        return tourDTO;
    }

    @Override
    public TourResponse createTour(TourRequest tourDTO) {
        TourEntity tourEntity = new TourEntity();
        modelMapper.map(tourDTO, tourEntity);
        List<TourCategoryEntity> tourCategories = tourCategoryRepository.findAllById(tourDTO.getCategory_id());
        tourEntity.setTourCategories(tourCategories);
        TourEntity result = tourRepository.save(tourEntity);
        return tourDTOConverter.toTourDTO(result);
    }

    @Override
    public TourResponse editTour(TourRequest tourDTO) {
        TourEntity tourEntity = tourRepository.findById(tourDTO.getId()).get();
        modelMapper.map(tourDTO, tourEntity);
//        Xoa danh muc cu
        tourEntity.getTourCategories().clear();
//        Them danh muc moi
        if (tourDTO.getCategory_id() != null && !tourDTO.getCategory_id().isEmpty()) {
            List<TourCategoryEntity> tourCategories = tourCategoryRepository.findAllById(tourDTO.getCategory_id());
            tourEntity.setTourCategories(tourCategories);
        }
        TourEntity result = tourRepository.save(tourEntity);
        return tourDTOConverter.toTourDTO(result);
    }

    @Transactional
    @Override
    public void deleteTour(List<Long> ids) {
        tourRepository.deleteTour(ids);
    }
}

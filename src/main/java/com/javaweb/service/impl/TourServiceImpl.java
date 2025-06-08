package com.javaweb.service.impl;

import com.javaweb.builder.TourSearchBuilder;
import com.javaweb.converter.TourDTOConverter;
import com.javaweb.converter.TourSearchBuilderConverter;
import com.javaweb.model.TourDTO;
import com.javaweb.model.TourResponse;
import com.javaweb.repository.TourRepository;
import com.javaweb.repository.entity.TourEntity;
import com.javaweb.service.TourService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TourServiceImpl implements TourService {
    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private TourDTOConverter tourDTOConverter;

    @Autowired
    private TourSearchBuilderConverter tourSearchBuilderConverter;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<TourDTO> findAll(Map<String, Object> params) {
        TourSearchBuilder tourSearchBuilders = tourSearchBuilderConverter.toTourSearchBuilder(params);
        List<TourEntity> tourEntities = tourRepository.findAll(tourSearchBuilders);
        List<TourDTO> result = new ArrayList<>();
        for(TourEntity item : tourEntities){
            TourDTO tourDTO = tourDTOConverter.toTourDTO(item);
            result.add(tourDTO);
        }
        return result;
    }

    @Override
    public TourDTO findById(Long id) {
        TourEntity tourEntity = tourRepository.findById(id).get();
        TourDTO tourDTO = tourDTOConverter.toTourDTO(tourEntity);
        return tourDTO;
    }

    @Override
    public TourResponse createTour(TourDTO tourDTO) {
        TourEntity tourEntity = new TourEntity();
        modelMapper.map(tourDTO, tourEntity);
        tourRepository.save(tourEntity);
        return new TourResponse("success", "Thêm thành công tour");
    }

    @Override
    public TourResponse editTour(TourDTO tourDTO) {
        TourEntity tourEntity = tourRepository.findById(tourDTO.getId()).get();
        modelMapper.map(tourDTO, tourEntity);
        tourRepository.save(tourEntity);
        return new TourResponse("success", "Cập nhật thành công tour");
    }

    @Transactional
    @Override
    public TourResponse deleteTour(List<Long> ids) {
        tourRepository.deleteByIdIn(ids);
        return new TourResponse("success", "Xóa thành công tour");
    }
}

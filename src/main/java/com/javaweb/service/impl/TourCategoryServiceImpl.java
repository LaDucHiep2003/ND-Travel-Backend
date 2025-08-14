package com.javaweb.service.impl;

import com.javaweb.builder.TourCategorySearchBuilder;
import com.javaweb.converter.TourCategoryDTOConverter;
import com.javaweb.converter.TourCategorySearchBuilderConverter;
import com.javaweb.model.TourCategoryDTO;
import com.javaweb.model.TourResponse;
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
    public List<TourCategoryDTO> findAll(Map<String, Object> params) {
        TourCategorySearchBuilder tourCategorySearchBuilder = tourCategorySearchBuilderConverter.tourCategorySearchBuilder(params);
        List<TourCategoryEntity> tourCategoryEntities = tourCategoryRepository.findAll(tourCategorySearchBuilder);
        List<TourCategoryDTO> result = new ArrayList<>();
        for (TourCategoryEntity tourCategoryEntity : tourCategoryEntities) {
            TourCategoryDTO tourCategoryDTO = tourCategoryDTOConverter.tourCategoryDTO(tourCategoryEntity);
            result.add(tourCategoryDTO);
        }
        return result;
    }

    @Override
    public TourCategoryDTO findById(Long id) {
        TourCategoryEntity tourCategoryEntities = tourCategoryRepository.findById(id).get();
        TourCategoryDTO tourCategoryDTO = tourCategoryDTOConverter.tourCategoryDTO(tourCategoryEntities);
        return tourCategoryDTO;
    }

    @Override
    public TourResponse createCategory(TourCategoryDTO tourCategoryDTO) {
        TourCategoryEntity tourCategoryEntity = new TourCategoryEntity();
        modelMapper.map(tourCategoryDTO, tourCategoryEntity);
        tourCategoryRepository.save(tourCategoryEntity);
        return new TourResponse("success", "Tạo thành công danh mục");
    }

    @Override
    public TourResponse updateCategory(TourCategoryDTO tourCategoryDTO) {
        TourCategoryEntity tourCategoryEntity = tourCategoryRepository.findById(tourCategoryDTO.getId()).get();
        modelMapper.map(tourCategoryDTO, tourCategoryEntity);
        tourCategoryRepository.save(tourCategoryEntity);
        return new TourResponse("success", "Cập nhật thành công danh mục");
    }

    @Transactional
    @Override
    public TourResponse deleteCategory(List<Long> ids) {
        tourCategoryRepository.deleteCategory(ids);
        return new TourResponse("success", "Xóa thành công danh mục");
    }
}

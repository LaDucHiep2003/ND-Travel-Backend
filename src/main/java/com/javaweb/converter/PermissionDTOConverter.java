package com.javaweb.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.model.PermissionDTO;
import com.javaweb.repository.entity.PermissionEntity;

@Component
public class PermissionDTOConverter {
    @Autowired 
    private ModelMapper modelMapper;

    public PermissionDTO toPermissionDTO(PermissionEntity permissionEntity) {
        return modelMapper.map(permissionEntity, PermissionDTO.class);
    }

    public PermissionEntity toPermissionEntity(PermissionDTO permissionDTO) {
        return modelMapper.map(permissionDTO, PermissionEntity.class);
    }
}

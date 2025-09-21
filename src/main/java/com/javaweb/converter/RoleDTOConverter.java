package com.javaweb.converter;

import com.javaweb.model.RoleDTO;
import com.javaweb.model.response.RoleResponse;
import com.javaweb.repository.entity.RoleEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleDTOConverter {
    @Autowired
    private ModelMapper modelMapper;

    public RoleResponse toRoleDTO(RoleEntity item){
        return modelMapper.map(item, RoleResponse.class);
    }

    public RoleEntity toRoleEntity(RoleDTO item){
        return modelMapper.map(item, RoleEntity.class);
    }
}

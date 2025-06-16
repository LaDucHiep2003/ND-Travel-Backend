package com.javaweb.converter;

import com.javaweb.model.RoleDTO;
import com.javaweb.repository.entity.RoleEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleDTOConverter {
    @Autowired
    private ModelMapper modelMapper;

    public RoleDTO toRoleDTO(RoleEntity item){
        return modelMapper.map(item, RoleDTO.class);
    }

    public RoleEntity toRoleEntity(RoleDTO item){
        return modelMapper.map(item, RoleEntity.class);
    }
}

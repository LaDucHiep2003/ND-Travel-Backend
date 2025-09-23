package com.javaweb.converter;

import com.javaweb.model.RoleDTO;
import com.javaweb.model.request.RoleRequest;
import com.javaweb.model.response.PermissionResponse;
import com.javaweb.model.response.RoleResponse;
import com.javaweb.repository.entity.PermissionEntity;
import com.javaweb.repository.entity.RoleEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleDTOConverter {
    @Autowired
    private ModelMapper modelMapper;

    public RoleResponse toRoleDTO(RoleEntity item){
        RoleResponse dto = modelMapper.map(item, RoleResponse.class);

        // Map permissions thủ công
        if (item.getPermissions() != null) {
            List<PermissionResponse> permissionResponses = item.getPermissions()
                    .stream()
                    .map(permission -> modelMapper.map(permission, PermissionResponse.class))
                    .collect(Collectors.toList());

            dto.setPermissions(permissionResponses);
        }

        return dto;
    }

    public RoleEntity toRoleEntity(RoleRequest item){
        return modelMapper.map(item, RoleEntity.class);
    }
}

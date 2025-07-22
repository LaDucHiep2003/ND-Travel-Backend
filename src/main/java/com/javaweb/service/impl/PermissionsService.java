package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.javaweb.model.TourResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.converter.PermissionDTOConverter;
import com.javaweb.model.PermissionDTO;
import com.javaweb.repository.PermissionRepository;
import com.javaweb.repository.entity.PermissionEntity;
import com.javaweb.service.PermissionsServive;

@Service
public class PermissionsService implements PermissionsServive {

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PermissionDTOConverter permissionDTOConverter;

    @Override
    public List<PermissionDTO> findAll() {
        List<PermissionEntity> permissionEntities = permissionRepository.findAll();
        List<PermissionDTO> result = new ArrayList<>();
        for (PermissionEntity permissionEntity : permissionEntities) {
            PermissionDTO permissionDTO = permissionDTOConverter.toPermissionDTO(permissionEntity);
            result.add(permissionDTO);
        }
        return result;
    }

    @Override
    public TourResponse createPermission(PermissionDTO permission) {
        PermissionEntity permissionEntity = permissionDTOConverter.toPermissionEntity(permission);
        permissionRepository.save(permissionEntity);
        return new TourResponse("success", "Thêm thành công Role");
    }
}

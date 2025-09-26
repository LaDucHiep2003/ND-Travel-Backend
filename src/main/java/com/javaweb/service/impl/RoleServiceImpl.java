package com.javaweb.service.impl;

import com.javaweb.builder.RoleSearchBuilder;
import com.javaweb.converter.RoleDTOConverter;
import com.javaweb.converter.RoleSearchBuilderConverter;
import com.javaweb.exception.AppException;
import com.javaweb.exception.ErrorCode;
import com.javaweb.model.request.RoleRequest;
import com.javaweb.model.response.RoleResponse;
import com.javaweb.repository.PermissionRepository;
import com.javaweb.repository.RoleRepository;
import com.javaweb.repository.entity.RoleEntity;
import com.javaweb.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleSearchBuilderConverter roleSearchBuilderConverter;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleDTOConverter roleDTOConverter;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<RoleResponse> findALL(Map<String, Object> params) {
        RoleSearchBuilder roleSearchBuilder = roleSearchBuilderConverter.toRoleSearchBuilder(params);
        List<RoleEntity> roleEntities = roleRepository.findAll(roleSearchBuilder);
        List<RoleResponse> result = new ArrayList<>();
        for(RoleEntity roleEntity : roleEntities) {
            RoleResponse roleDTO = roleDTOConverter.toRoleDTO(roleEntity);
            result.add(roleDTO);
        }
        return result;
    }

    @Override
    public RoleResponse findById(Long id) {
        RoleEntity roleEntity = roleRepository.findById(id).get();
        RoleResponse result = roleDTOConverter.toRoleDTO(roleEntity);
        return result;
    }

    @Override
    public RoleResponse createRole(RoleRequest role) {
        RoleEntity roleEntity = roleDTOConverter.toRoleEntity(role);
        var permissions = permissionRepository.findAllById(role.getPermissionIds());
        roleEntity.setPermissions(new ArrayList<>(permissions));
        RoleEntity result = roleRepository.save(roleEntity);

        return roleDTOConverter.toRoleDTO(result);
    }

    @Override
    public RoleResponse updateRole(RoleRequest role) {
        RoleEntity roleEntity = roleRepository.findById(role.getId())
                        .orElseThrow(()->new AppException(ErrorCode.ROLE_EXISTED));
        modelMapper.map(role, roleEntity);
        if (role.getPermissionIds() != null) {
            var permissions = permissionRepository.findAllById(role.getPermissionIds());
            roleEntity.setPermissions(new ArrayList<>(permissions));
        }
        RoleEntity result = roleRepository.save(roleEntity);

        return roleDTOConverter.toRoleDTO(result);
    }

    @Transactional
    @Override
    public void deleteRole(List<Long> ids) {
        roleRepository.deleteRole(ids);
    }
}

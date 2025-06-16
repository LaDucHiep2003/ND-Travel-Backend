package com.javaweb.service.impl;

import com.javaweb.builder.RoleSearchBuilder;
import com.javaweb.converter.RoleDTOConverter;
import com.javaweb.converter.RoleSearchBuilderConverter;
import com.javaweb.model.RoleDTO;
import com.javaweb.model.TourDTO;
import com.javaweb.model.TourResponse;
import com.javaweb.repository.RoleRepository;
import com.javaweb.repository.entity.RoleEntity;
import com.javaweb.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private ModelMapper modelMapper;

    @Override
    public List<RoleDTO> findALL(Map<String, Object> params) {
        RoleSearchBuilder roleSearchBuilder = roleSearchBuilderConverter.toRoleSearchBuilder(params);
        List<RoleEntity> roleEntities = roleRepository.findAll(roleSearchBuilder);
        List<RoleDTO> result = new ArrayList<>();
        for(RoleEntity roleEntity : roleEntities) {
            RoleDTO roleDTO = roleDTOConverter.toRoleDTO(roleEntity);
            result.add(roleDTO);
        }
        return result;
    }

    @Override
    public RoleDTO findById(Long id) {
        RoleEntity roleEntity = roleRepository.findById(id).get();
        RoleDTO result = roleDTOConverter.toRoleDTO(roleEntity);
        return result;
    }

    @Override
    public TourResponse createRole(RoleDTO role) {
        RoleEntity roleEntity = new RoleEntity();
        modelMapper.map(role, roleEntity);
        roleRepository.save(roleEntity);

        return new TourResponse("success", "Thêm quyền thành công");
    }

    @Override
    public TourResponse updateRole(RoleDTO role) {
        RoleEntity roleEntity = roleRepository.findById(role.getId()).get();
        modelMapper.map(role, roleEntity);
        roleRepository.save(roleEntity);

        return new TourResponse("success", "Chỉnh sửa quyền thành công");
    }

    @Override
    public TourResponse deleteRole(List<Long> ids) {
        roleRepository.deleteRole(ids);
        return new TourResponse("success", "Xóa quyền thành công");
    }
}

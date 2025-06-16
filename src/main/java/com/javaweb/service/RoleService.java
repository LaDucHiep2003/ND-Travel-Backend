package com.javaweb.service;

import com.javaweb.model.RoleDTO;
import com.javaweb.model.TourResponse;

import java.util.List;
import java.util.Map;

public interface RoleService {
    List<RoleDTO> findALL(Map<String,Object> params);
    RoleDTO findById(Long id);
    TourResponse createRole(RoleDTO role);
    TourResponse updateRole(RoleDTO role);
    TourResponse deleteRole(List<Long> ids);
}

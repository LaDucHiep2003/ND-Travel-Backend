package com.javaweb.service;

import com.javaweb.model.request.RoleRequest;
import com.javaweb.model.response.RoleResponse;

import java.util.List;
import java.util.Map;

public interface RoleService {
    List<RoleResponse> findALL(Map<String,Object> params);
    RoleResponse findById(Long id);
    RoleResponse createRole(RoleRequest role);
    RoleResponse updateRole(RoleRequest role);
    void deleteRole(List<Long> ids);
}

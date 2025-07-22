package com.javaweb.service;

import java.util.List;

import com.javaweb.model.PermissionDTO;
import com.javaweb.model.TourResponse;

public interface PermissionsServive {
    List<PermissionDTO> findAll();
    TourResponse createPermission(PermissionDTO permission);
} 
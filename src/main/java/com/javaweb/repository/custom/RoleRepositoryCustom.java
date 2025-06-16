package com.javaweb.repository.custom;

import com.javaweb.builder.RoleSearchBuilder;
import com.javaweb.repository.entity.RoleEntity;

import java.util.List;

public interface RoleRepositoryCustom {
    List<RoleEntity> findAll(RoleSearchBuilder roleSearchBuilder);
    void deleteRole(List<Long> ids);
}

package com.javaweb.repository;

import com.javaweb.repository.custom.RoleRepositoryCustom;
import com.javaweb.repository.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long>, RoleRepositoryCustom {
}

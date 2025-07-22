package com.javaweb.repository;

import com.javaweb.repository.custom.UserRepositoryCustom;
import com.javaweb.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long>, UserRepositoryCustom {
    UserEntity findByUsername(String username);
} 
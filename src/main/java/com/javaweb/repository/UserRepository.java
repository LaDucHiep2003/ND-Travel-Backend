package com.javaweb.repository;

import com.javaweb.repository.custom.UserRepositoryCustom;
import com.javaweb.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long>, UserRepositoryCustom {
    Optional<UserEntity> findByUsername(String username);
    Boolean existsByUsername(String username);
} 
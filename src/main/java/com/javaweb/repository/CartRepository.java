package com.javaweb.repository;

import com.javaweb.repository.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<CartEntity, Integer> {
    Optional<CartEntity> findByUserId(Long userId);
}

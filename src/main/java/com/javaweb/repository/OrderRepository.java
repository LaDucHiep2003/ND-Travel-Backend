package com.javaweb.repository;

import com.javaweb.repository.custom.OrderRepositoryCustom;
import com.javaweb.repository.entity.OrderEntity;
import com.javaweb.repository.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long>, OrderRepositoryCustom {
    Page<OrderEntity> findByUserAndStatusContainingOrderByCreatedAtDesc(UserEntity user, String status, Pageable pageable);
}

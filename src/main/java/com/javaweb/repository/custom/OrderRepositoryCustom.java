package com.javaweb.repository.custom;

import com.javaweb.builder.OrderSearchBuilder;
import com.javaweb.repository.entity.OrderEntity;

import java.util.List;

public interface OrderRepositoryCustom {
    List<OrderEntity> findALl(OrderSearchBuilder orderSearchBuilder);
    void delete(List<Long> ids);
    void confirmOrders(List<Long> ids);
}

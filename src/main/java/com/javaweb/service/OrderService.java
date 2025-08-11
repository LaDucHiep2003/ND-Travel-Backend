package com.javaweb.service;

import com.javaweb.model.OrderDTO;
import com.javaweb.model.response.ApiResponse;
import com.javaweb.repository.entity.OrderEntity;


import java.util.List;
import java.util.Map;

public interface OrderService {
    List<OrderDTO> findAll(Map<String, Object> params);
    OrderDTO findById(Long id);
    ApiResponse<OrderEntity> edit(OrderDTO dto);
    ApiResponse<OrderEntity> order(OrderDTO order);
    ApiResponse<OrderEntity> delete(List<Long> ids);
    ApiResponse<OrderEntity> confirm(List<Long> ids);
}

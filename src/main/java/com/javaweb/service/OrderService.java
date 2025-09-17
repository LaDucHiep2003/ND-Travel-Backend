package com.javaweb.service;

import com.javaweb.model.OrderDTO;
import com.javaweb.model.ApiResponse;
import com.javaweb.model.request.OrderRequest;
import com.javaweb.model.response.OrderResponse;
import com.javaweb.repository.entity.OrderEntity;


import java.util.List;
import java.util.Map;

public interface OrderService {
    List<OrderResponse> findAll(Map<String, Object> params);
    OrderResponse findById(Long id);
    OrderResponse edit(OrderRequest orderRequest);
    OrderResponse order(OrderRequest order);
    void delete(List<Long> ids);
    void confirm(List<Long> ids);
}

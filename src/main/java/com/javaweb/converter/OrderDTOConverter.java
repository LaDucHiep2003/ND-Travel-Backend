package com.javaweb.converter;

import com.javaweb.model.OrderDTO;
import com.javaweb.model.OrderItemDTO;
import com.javaweb.model.response.OrderResponse;
import com.javaweb.repository.entity.OrderEntity;
import com.javaweb.repository.entity.OrderItemEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderDTOConverter {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderItemDTOConverter orderItemDTOConverter;

    public OrderResponse toOrderDTO(OrderEntity orderEntity) {
        OrderResponse orderDTO = modelMapper.map(orderEntity, OrderResponse.class);
        
        // Set user info
        if (orderEntity.getUser() != null) {
            orderDTO.setUserId(orderEntity.getUser().getId());
            orderDTO.setUserName(orderEntity.getUser().getUsername());
        }
        
        // Convert order items
        if (orderEntity.getOrderItems() != null && !orderEntity.getOrderItems().isEmpty()) {
            List<OrderItemDTO> orderItemDTOs = new ArrayList<>();
            for (OrderItemEntity orderItemEntity : orderEntity.getOrderItems()) {
                OrderItemDTO orderItemDTO = orderItemDTOConverter.toOrderItemDTO(orderItemEntity);
                orderItemDTOs.add(orderItemDTO);
            }
            orderDTO.setOrderItems(orderItemDTOs);
        }
        
        return orderDTO;
    }

    public OrderEntity toOrderEntity(OrderDTO orderDTO) {
        return modelMapper.map(orderDTO, OrderEntity.class);
    }
}

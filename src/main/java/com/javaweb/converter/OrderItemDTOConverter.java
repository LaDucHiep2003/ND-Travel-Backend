package com.javaweb.converter;

import com.javaweb.model.OrderItemDTO;
import com.javaweb.repository.entity.OrderItemEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderItemDTOConverter {
    @Autowired
    private ModelMapper modelMapper;

    public OrderItemDTO toOrderItemDTO(OrderItemEntity orderItemEntity) {
        OrderItemDTO orderItemDTO = modelMapper.map(orderItemEntity, OrderItemDTO.class);
        if (orderItemEntity.getTour() != null) {
            orderItemDTO.setTourId(orderItemEntity.getTour().getId());
            orderItemDTO.setTourName(orderItemEntity.getTour().getTitle());
            orderItemDTO.setThumbnail(orderItemEntity.getTour().getThumbnail());
        }
        return orderItemDTO;
    }

    public OrderItemEntity toOrderItemEntity(OrderItemDTO orderItemDTO) {
        return modelMapper.map(orderItemDTO, OrderItemEntity.class);
    }
}

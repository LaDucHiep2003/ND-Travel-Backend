package com.javaweb.converter;

import com.javaweb.model.response.CartItemsResponse;
import com.javaweb.model.response.CartResponse;
import com.javaweb.model.response.UserResponse;
import com.javaweb.repository.entity.CartEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartDTOConverter {
    @Autowired
    private ModelMapper modelMapper;

    public CartResponse toCartDTO(CartEntity entity) {
        return CartResponse.builder()
                .id(entity.getId())
                .user(UserResponse.builder()
                        .id(entity.getUser().getId())
                        .username(entity.getUser().getUsername())
                        .build())
                .build();
    }
}

package com.javaweb.converter;

import com.javaweb.model.CartDTO;
import com.javaweb.model.response.CartResponse;
import com.javaweb.repository.entity.CartEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartDTOConverter {
    @Autowired
    private ModelMapper modelMapper;

    public CartResponse toCartDTO(CartEntity cartEntity) {
        return modelMapper.map(cartEntity, CartResponse.class);
    }
}

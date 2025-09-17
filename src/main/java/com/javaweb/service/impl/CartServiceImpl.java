package com.javaweb.service.impl;

import com.javaweb.converter.CartDTOConverter;
import com.javaweb.model.CartDTO;
import com.javaweb.repository.CartRepository;
import com.javaweb.repository.entity.CartEntity;
import com.javaweb.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartDTOConverter cartDTOConverter;

    @Override
    public List<CartDTO> getAll() {
        List<CartEntity> list = cartRepository.findAll();
        List<CartDTO> result = new ArrayList<>();
        for (CartEntity cartEntity : list) {
            CartDTO cartDTO = cartDTOConverter.toCartDTO(cartEntity);
            result.add(cartDTO);
        }
        return result;
    }
}

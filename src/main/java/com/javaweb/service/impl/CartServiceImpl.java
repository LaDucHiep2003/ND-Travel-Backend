package com.javaweb.service.impl;

import com.javaweb.converter.CartDTOConverter;
import com.javaweb.model.response.CartResponse;
import com.javaweb.repository.CartRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.repository.entity.CartEntity;
import com.javaweb.repository.entity.UserEntity;
import com.javaweb.service.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CartDTOConverter cartDTOConverter;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<CartResponse> getAll() {
        List<CartEntity> list = cartRepository.findAll();
        List<CartResponse> result = new ArrayList<>();
        for (CartEntity cartEntity : list) {
            CartResponse cartDTO = cartDTOConverter.toCartDTO(cartEntity);
            result.add(cartDTO);
        }
        return result;
    }

    @Override
    public CartResponse myCart() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        CartEntity cart = cartRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        return cartDTOConverter.toCartDTO(cart);
    }
}

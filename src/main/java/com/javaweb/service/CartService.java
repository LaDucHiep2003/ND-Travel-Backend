package com.javaweb.service;

import com.javaweb.model.CartDTO;

import java.util.List;

public interface CartService {
    List<CartDTO> getAll();
}

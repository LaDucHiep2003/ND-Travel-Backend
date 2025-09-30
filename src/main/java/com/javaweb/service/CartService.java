package com.javaweb.service;

import com.javaweb.model.response.CartResponse;

import java.util.List;

public interface CartService {
    List<CartResponse> getAll();
}

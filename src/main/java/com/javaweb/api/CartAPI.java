package com.javaweb.api;


import com.javaweb.model.ApiResponse;
import com.javaweb.model.response.CartResponse;
import com.javaweb.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/")
public class CartAPI {
    @Autowired
    private CartService cartService;

    @GetMapping("carts")
    public ApiResponse<List<CartResponse>> getAll() {
        return ApiResponse.<List<CartResponse>>builder()
                .result(cartService.getAll())
                .build();
    }

    @GetMapping("myCart")
    public ApiResponse<CartResponse> myCart() {
        return ApiResponse.<CartResponse>builder()
                .result(cartService.myCart())
                .build();
    }
}

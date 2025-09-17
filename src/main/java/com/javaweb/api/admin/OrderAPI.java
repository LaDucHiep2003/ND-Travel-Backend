package com.javaweb.api.admin;


import com.javaweb.model.OrderDTO;
import com.javaweb.model.ApiResponse;
import com.javaweb.model.request.OrderRequest;
import com.javaweb.model.response.OrderResponse;
import com.javaweb.repository.entity.OrderEntity;
import com.javaweb.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/admin")
public class OrderAPI {
    @Autowired
    private OrderService orderService;

    @GetMapping("orders")
    public List<OrderResponse> findALl(@RequestParam Map<String, Object> params){
        List<OrderResponse> result = orderService.findAll(params);
        return result;
    }

    @GetMapping("orders/{id}")
    public OrderResponse findById(@PathVariable Long id){
        return orderService.findById(id);
    }

    @PostMapping("orders")
    public ApiResponse<OrderResponse> order(@RequestBody OrderRequest orderRequest){
        ApiResponse<OrderResponse> apiResponse = new ApiResponse<>();
        OrderResponse result = orderService.order(orderRequest);
        apiResponse.setResult(result);
        return apiResponse;
    }

    @PutMapping("orders")
    public ApiResponse<OrderResponse> edit(@RequestBody OrderRequest orderDTO){
        ApiResponse<OrderResponse> apiResponse = new ApiResponse<>();
        OrderResponse result = orderService.order(orderDTO);
        apiResponse.setResult(result);
        return apiResponse;
    }

    @DeleteMapping("orders")
    public String delete(@RequestParam List<Long> ids){
        orderService.delete(ids);
        return "User has been deleted";
    }

    @PostMapping("orders/confirm")
    public String confirm(@RequestBody List<Long> ids){
        orderService.confirm(ids);
        return "Order has been confirmed";
    }
}

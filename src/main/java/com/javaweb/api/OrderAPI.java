package com.javaweb.api;


import com.javaweb.model.ApiResponse;
import com.javaweb.model.request.OrderRequest;
import com.javaweb.model.response.OrderResponse;
import com.javaweb.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class OrderAPI {
    @Autowired
    private OrderService orderService;

    @GetMapping("orders")
    public ApiResponse<List<OrderResponse>> findALl(@RequestParam Map<String, Object> params){
        return ApiResponse.<List<OrderResponse>>builder()
                .result(orderService.findAll(params))
                .build();
    }

    @GetMapping("orders/{id}")
    public ApiResponse<OrderResponse> findById(@PathVariable Long id){
        return ApiResponse.<OrderResponse>builder()
                .result(orderService.findById(id))
                .build();
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
    public ApiResponse<String> delete(@RequestParam List<Long> ids){
        orderService.delete(ids);
        return ApiResponse.<String>builder()
                .result("Order has been deleted")
                .build();
    }

    @PostMapping("orders/confirm")
    public ApiResponse<String> confirm(@RequestBody List<Long> ids){
        orderService.confirm(ids);
        return ApiResponse.<String>builder()
                .result("Order has been Confirmed")
                .build();
    }
}

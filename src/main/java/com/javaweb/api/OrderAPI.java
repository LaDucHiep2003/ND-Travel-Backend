package com.javaweb.api;


import com.javaweb.model.OrderDTO;
import com.javaweb.model.response.ApiResponse;
import com.javaweb.repository.entity.OrderEntity;
import com.javaweb.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public List<OrderDTO> findALl(Map<String, Object> params){
        List<OrderDTO> result = orderService.findAll(params);
        return result;
    }

    @GetMapping("orders/{id}")
    public OrderDTO findById(@PathVariable Long id){
        return orderService.findById(id);
    }

    @PostMapping("orders")
    public ResponseEntity<ApiResponse<OrderEntity>> order(@RequestBody OrderDTO orderDTO){
        ApiResponse<OrderEntity> result = orderService.order(orderDTO);
        return ResponseEntity.ok(result);
    }

    @PutMapping("orders")
    public ResponseEntity<ApiResponse<OrderEntity>> edit(@RequestBody OrderDTO orderDTO){
        ApiResponse<OrderEntity> result = orderService.edit(orderDTO);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("orders")
    public ResponseEntity<ApiResponse<OrderEntity>> delete(List<Long> ids){
        ApiResponse<OrderEntity> result = orderService.delete(ids);
        return ResponseEntity.ok(result);
    }
}

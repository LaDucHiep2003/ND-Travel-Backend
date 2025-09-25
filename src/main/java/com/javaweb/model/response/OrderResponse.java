package com.javaweb.model.response;

import com.javaweb.model.OrderItemDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
    Long id;
    Long userId;
    String userName;
    LocalDateTime orderDate;
    BigDecimal totalPrice;
    String status;
    String paymentMethod;
    String note;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    List<OrderItemDTO> orderItems = new ArrayList<>();
    String customer_name;
}

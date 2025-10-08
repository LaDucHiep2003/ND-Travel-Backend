package com.javaweb.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItemsResponse {
     Long id;
     Long cart_id;
     Long tour_id;
     String type;
     Integer quantity;
     Integer price;
}

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
     Long tourId;
     String tourName;
     Integer quantity;
     BigDecimal unitPrice;
     BigDecimal subtotal;
     String thumbnail;
}

package com.javaweb.model.request;

import com.javaweb.model.UserDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartRequest {
     Long id;
     Long userId;
     List<Long> CartItemsId;
}

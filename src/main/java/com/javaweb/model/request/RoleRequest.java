package com.javaweb.model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleRequest {
    Long id;
    String name;
    String code;
    String description;
    Boolean deleted = false;
    List<Long> permissionIds;
}

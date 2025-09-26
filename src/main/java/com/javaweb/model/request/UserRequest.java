package com.javaweb.model.request;

import com.javaweb.model.RoleDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {
     Long id;
     String username;
     String email;
     String fullname;
     String password;
     @Builder.Default
     Boolean deleted = false;
     List<Long> roleId;
}

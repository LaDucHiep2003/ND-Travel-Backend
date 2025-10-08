package com.javaweb.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
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

     @Email
     String email;
     String fullname;

     @Size(min = 8, max = 20, message = "INVALID_PASSWORD")
     String password;
     @Builder.Default
     Boolean deleted = false;
     List<Long> roleId;
}

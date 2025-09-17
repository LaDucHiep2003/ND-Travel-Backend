package com.javaweb.converter;

import com.javaweb.model.UserDTO;
import com.javaweb.model.request.UserRequest;
import com.javaweb.model.response.UserResponse;
import com.javaweb.repository.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDTOConverter {
    @Autowired
    private ModelMapper modelMapper;

    public UserResponse toUserDTO(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserResponse.class);
    }

    public UserEntity toUserEntity(UserRequest userRequest) {
        return modelMapper.map(userRequest, UserEntity.class);
    }

} 
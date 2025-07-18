package com.javaweb.repository.custom;

import com.javaweb.builder.UserSearchBuilder;
import com.javaweb.repository.entity.UserEntity;

import java.util.List;

public interface UserRepositoryCustom {
    List<UserEntity> findAll(UserSearchBuilder userSearchBuilder);
    void deleteUser(List<Long> ids);
} 

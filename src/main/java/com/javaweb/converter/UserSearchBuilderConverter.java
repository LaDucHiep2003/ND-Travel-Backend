package com.javaweb.converter;

import com.javaweb.builder.UserSearchBuilder;
import com.javaweb.utils.MapUtil;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserSearchBuilderConverter {
    public UserSearchBuilder toUserSearchBuilder(Map<String, Object> params){
        UserSearchBuilder userSearchBuilder = new UserSearchBuilder.Builder()
                .setUsername(MapUtil.getObject(params, "username", String.class))
                .setEmail(MapUtil.getObject(params, "email", String.class))
                .setFullname(MapUtil.getObject(params, "fullname", String.class))
                .setRole(MapUtil.getObject(params, "role", Long.class))
                .build();
        return userSearchBuilder;
    }
}

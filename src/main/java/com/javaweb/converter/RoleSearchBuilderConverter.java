package com.javaweb.converter;

import com.javaweb.builder.RoleSearchBuilder;
import com.javaweb.utils.MapUtil;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RoleSearchBuilderConverter {
    public RoleSearchBuilder toRoleSearchBuilder(Map<String, Object> params) {
        RoleSearchBuilder toRoleSearchBuilder = new RoleSearchBuilder.Builder()
                .setId(MapUtil.getObject(params, "id", Long.class))
                .setCode(MapUtil.getObject(params, "code", String.class))
                .setName(MapUtil.getObject(params, "name", String.class))
                .build();
        return toRoleSearchBuilder;
    }
}

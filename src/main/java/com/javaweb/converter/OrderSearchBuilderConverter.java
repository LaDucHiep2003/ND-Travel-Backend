package com.javaweb.converter;

import com.javaweb.builder.OrderSearchBuilder;
import com.javaweb.utils.MapUtil;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Component
public class OrderSearchBuilderConverter {
    public OrderSearchBuilder toOrderSearchBuilder(Map<String, Object> params){
        OrderSearchBuilder toOrderSearchBuilder = new OrderSearchBuilder.Builder()
                .setId(MapUtil.getObject(params, "id", Long.class))
                .setStatus(MapUtil.getObject(params, "status", String.class))
                .setTotalPrice(MapUtil.getObject(params, "totalPrice", BigDecimal.class))
                .build();
        return toOrderSearchBuilder;
    }
}

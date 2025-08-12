package com.javaweb.converter;

import com.javaweb.builder.TourSearchBuilder;
import com.javaweb.utils.MapUtil;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Map;

@Component
public class TourSearchBuilderConverter {
    public TourSearchBuilder toTourSearchBuilder(Map<String, Object> params) {
        TourSearchBuilder tourSearchBuilder = new TourSearchBuilder.Builder()
                .setId(MapUtil.getObject(params, "id", Long.class))
                .setDeparture_from(MapUtil.getObject(params, "departure_from", String.class))
                .setDestination(MapUtil.getObject(params, "destination", String.class))
                .setDuration(MapUtil.getObject(params, "duration", String.class))
                .setTitle(MapUtil.getObject(params, "title", String.class))
                .setTransport(MapUtil.getObject(params, "transport", String.class))
                .setDeparture_date(MapUtil.getObject(params, "departure_date", LocalDate.class))
                .setEnd_date(MapUtil.getObject(params, "end_date", LocalDate.class))
                .setStatus(MapUtil.getObject(params, "status", String.class))
                .build();
        return tourSearchBuilder;
    }
}

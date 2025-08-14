package com.javaweb.converter;

import com.javaweb.builder.TourCategorySearchBuilder;
import com.javaweb.utils.MapUtil;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TourCategorySearchBuilderConverter {
    public TourCategorySearchBuilder tourCategorySearchBuilder(Map<String, Object> params) {
        TourCategorySearchBuilder tourCategorySearchBuilder = new TourCategorySearchBuilder.Builder()
                .setSubCategory(MapUtil.getObject(params, "subCategory", Integer.class))
                .setParent_id(MapUtil.getObject(params, "parentId", Integer.class))
                .build();

        return tourCategorySearchBuilder;
    }
}

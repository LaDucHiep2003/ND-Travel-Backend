package com.javaweb.model.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TourCategoryResponse {
    Long id;
    String name;
    String description;
    String image;
    Long parentId;
}

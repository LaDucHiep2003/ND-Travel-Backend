package com.javaweb.model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TourRequest {
     Long id;
     String title;
     String thumbnail;
     String images;
     String departure_from;
     String destination;
     String duration;
     Integer seats;
     String transport;
     String description;
     String itinerary;
     String location;
     Integer price_adult;
     Integer price_child;
     Integer price_infant;
     String status;
     LocalDate departure_date;
     LocalDate end_date;
     Double discount;
     List<Long> category_id;
     Boolean deleted = false;
}

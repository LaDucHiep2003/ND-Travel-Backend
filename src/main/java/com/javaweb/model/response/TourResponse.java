package com.javaweb.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TourResponse {
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
}

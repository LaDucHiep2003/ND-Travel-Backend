package com.javaweb.api;

import com.javaweb.model.TourItineraryDTO;
import com.javaweb.service.TourItineraryService;
import com.javaweb.repository.entity.ItineraryStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ScheduleAPI {

    @Autowired
    private TourItineraryService tourItineraryService;

    @GetMapping("/api/schedule")
    public List<TourItineraryDTO> getScheduleByTour(@RequestParam(name = "tour") Long tourId) {
        return tourItineraryService.getItinerariesByTourId(tourId);
    }

    @PatchMapping("/api/schedule/{id}")
    public ResponseEntity<TourItineraryDTO> updateItineraryStatus(
            @PathVariable Long id,
            @RequestParam(name = "status") ItineraryStatus status) {
        TourItineraryDTO result = tourItineraryService.updateItineraryStatus(id, status);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}



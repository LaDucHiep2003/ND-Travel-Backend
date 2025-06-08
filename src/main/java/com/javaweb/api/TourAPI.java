package com.javaweb.api;

import com.javaweb.model.TourDTO;
import com.javaweb.model.TourResponse;
import com.javaweb.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class TourAPI {
    @Autowired
    private TourService tourService;

    @GetMapping("/api/tours")
    public List<TourDTO> findAll(@RequestParam Map<String, Object> params) {
        List<TourDTO> result = tourService.findAll(params);
        return result;
    }

    @GetMapping("/api/tours/{id}")
    public TourDTO findById(@PathVariable Long id) {
        TourDTO result = tourService.findById(id);
        return result;
    }

    @PostMapping("/api/tours")
    public ResponseEntity<TourResponse> createTour(@RequestBody TourDTO tourDTO) {
        TourResponse msg = tourService.createTour(tourDTO);
        return ResponseEntity.ok(msg);
    }

    @PatchMapping("/api/tours")
    public ResponseEntity<TourResponse> updateTour(@RequestBody TourDTO tourDTO) {
        TourResponse msg = tourService.editTour(tourDTO);
        return ResponseEntity.ok(msg);
    }

    @DeleteMapping("/api/tours")
    public ResponseEntity<TourResponse> deleteTour(@RequestParam List<Long> ids) {
        TourResponse msg = tourService.deleteTour(ids);
        return ResponseEntity.ok(msg);
    }
}

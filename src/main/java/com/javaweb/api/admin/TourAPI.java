package com.javaweb.api.admin;

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
@RequestMapping("/api/admin")
public class TourAPI {
    @Autowired
    private TourService tourService;

    @GetMapping("/tours")
    public ResponseEntity<List<TourDTO>> findAll(@RequestParam Map<String, Object> params) {
        List<TourDTO> result = tourService.findAll(params);
        long total = tourService.count(params);
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(total))
                .body(result);
    }

    @GetMapping("/tours/{id}")
    public TourDTO findById(@PathVariable Long id) {
        TourDTO result = tourService.findById(id);
        return result;
    }

    @PostMapping("/tours")
    public ResponseEntity<TourResponse> createTour(@RequestBody TourDTO tourDTO) {
        TourResponse msg = tourService.createTour(tourDTO);
        return ResponseEntity.ok(msg);
    }

    @PatchMapping("/tours")
    public ResponseEntity<TourResponse> updateTour(@RequestBody TourDTO tourDTO) {
        TourResponse msg = tourService.editTour(tourDTO);
        return ResponseEntity.ok(msg);
    }

    @DeleteMapping("/tours")
    public ResponseEntity<TourResponse> deleteTour(@RequestParam List<Long> ids) {
        TourResponse msg = tourService.deleteTour(ids);
        return ResponseEntity.ok(msg);
    }
}

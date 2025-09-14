package com.javaweb.api.admin;

import com.javaweb.model.TourDTO;
import com.javaweb.model.TourResponse;
import com.javaweb.model.response.PageResponse;
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
    public ResponseEntity<PageResponse<TourDTO>> findAll(@RequestParam Map<String, Object> params) {
        int page = params.containsKey("page") ? Integer.parseInt((String) params.get("page")) : 1;
        int size = params.containsKey("size") ? Integer.parseInt((String) params.get("size")) : 10;
        List<TourDTO> result = tourService.findAll(params);
        long total = tourService.count(params);

        PageResponse<TourDTO> response = new PageResponse<>(result, total, page, size);

        return ResponseEntity.ok(response);
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

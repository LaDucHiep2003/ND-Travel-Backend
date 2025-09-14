package com.javaweb.api.client;

import com.javaweb.model.TourDTO;
import com.javaweb.model.response.PageResponse;
import com.javaweb.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/tours")
public class TourClientAPI {
    @Autowired
    private TourService tourService;

    @GetMapping("")
    public ResponseEntity<PageResponse<TourDTO>> findAll(@RequestParam Map<String, Object> params) {
        int page = params.containsKey("page") ? Integer.parseInt((String) params.get("page")) : 1;
        int size = params.containsKey("size") ? Integer.parseInt((String) params.get("size")) : 10;
        List<TourDTO> result = tourService.findAll(params);
        long total = tourService.count(params);

        PageResponse<TourDTO> response = new PageResponse<>(result, total, page, size);

        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public TourDTO findById(@PathVariable Long id) {
        TourDTO result = tourService.findById(id);
        return result;
    }
}

package com.javaweb.api.admin;

import com.javaweb.model.ApiResponse;
import com.javaweb.model.TourDTO;
import com.javaweb.model.request.TourRequest;
import com.javaweb.model.response.TourResponse;
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
    public PageResponse<TourResponse> findAll(@RequestParam Map<String, Object> params) {
        int page = params.containsKey("page") ? Integer.parseInt((String) params.get("page")) : 1;
        int size = params.containsKey("size") ? Integer.parseInt((String) params.get("size")) : 10;
        List<TourResponse> result = tourService.findAll(params);
        long total = tourService.count(params);

        PageResponse<TourResponse> response = new PageResponse<>(result, total, page, size);

        return response;
    }

    @GetMapping("/tours/{id}")
    public TourResponse findById(@PathVariable Long id) {
        TourResponse result = tourService.findById(id);
        return result;
    }

    @PostMapping("/tours")
    public ApiResponse<TourResponse> createTour(@RequestBody TourRequest tourDTO) {
        ApiResponse<TourResponse> apiResponse = new ApiResponse<>();
        TourResponse msg = tourService.createTour(tourDTO);
        apiResponse.setResult(msg);
        return apiResponse;
    }

    @PatchMapping("/tours")
    public ApiResponse<TourResponse> updateTour(@RequestBody TourRequest tourDTO) {
        ApiResponse<TourResponse> apiResponse = new ApiResponse<>();
        TourResponse msg = tourService.editTour(tourDTO);
        apiResponse.setResult(msg);
        return apiResponse;
    }

    @DeleteMapping("/tours")
    public String deleteTour(@RequestParam List<Long> ids) {
        tourService.deleteTour(ids);
        return "Tour has been deleted";
    }
}

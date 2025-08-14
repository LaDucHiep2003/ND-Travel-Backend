package com.javaweb.api.client;

import com.javaweb.model.TourDTO;
import com.javaweb.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class TourClientAPI {
    @Autowired
    private TourService tourService;

    @GetMapping("/tours")
    public List<TourDTO> findAll(@RequestParam Map<String, Object> params) {
        List<TourDTO> result = tourService.findAll(params);
        return result;
    }
}

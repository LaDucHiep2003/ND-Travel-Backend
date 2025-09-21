package com.javaweb.api.client;


import com.javaweb.model.TourCategoryDTO;
import com.javaweb.model.response.TourCategoryResponse;
import com.javaweb.service.TourCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class TourCategoryClient {

    @Autowired
    private TourCategoryService tourCategoryService;

    @GetMapping("/category")
    public List<TourCategoryResponse> findAll(@RequestParam Map<String, Object> params){
        return tourCategoryService.findAll(params);
    }
}

package com.tai.DetectiveConanV1.Review;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/api/v1/reviews")
@RestController
@CrossOrigin(origins = "http://localhost:3000")

public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Review>(
                reviewService.createReview(
                        payload.get("reviewBody"),
                        payload.get("MId"),
                        payload.get("writter")),
                HttpStatus.CREATED
        );
    }




}


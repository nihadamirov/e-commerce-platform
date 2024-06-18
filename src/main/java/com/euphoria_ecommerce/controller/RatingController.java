package com.euphoria_ecommerce.controller;

import com.euphoria_ecommerce.dto.RatingDto;
import com.euphoria_ecommerce.service.RatingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rating")
@RequiredArgsConstructor
public class RatingController {
    private final RatingService ratingService;

    @GetMapping("/{id}")
    public ResponseEntity<RatingDto> getRatingById(@PathVariable int id) {
        return new ResponseEntity<>(ratingService.getRatingById(id), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<RatingDto>> getAllRatings() {
        return new ResponseEntity<>(ratingService.getAllRatings(), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<RatingDto> createRate(@Valid @RequestBody RatingDto rating) {
        return new ResponseEntity<>(ratingService.createRating(rating), HttpStatus.CREATED);
    }

//    @PutMapping("/update/{id}")
//    public ResponseEntity<RatingDto> updateRate(@PathVariable int id, @RequestBody RatingDto rating) {
//        return new ResponseEntity<>(ratingService.updateRating(id, rating), HttpStatus.CREATED);
//    }
}

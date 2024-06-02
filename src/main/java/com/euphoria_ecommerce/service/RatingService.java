package com.euphoria_ecommerce.service;

import com.euphoria_ecommerce.model.Rating;
import com.euphoria_ecommerce.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingService {
    private final RatingRepository repository;

    public List<Rating> getAllRatings() {
        return repository.findAll();
    }

    public Rating getRatingById(int id) {
        return repository.findById(id).get();
    }

    public Rating createRating(Rating rating) {
        return repository.save(rating);
    }

    public Rating updateRating(int id, Rating rating) {
        Rating foundedRating = repository.findById(id).get();
        foundedRating.setRating(rating.getRating());
        foundedRating.setProduct(rating.getProduct());
        foundedRating.setUser(rating.getUser());
        return repository.save(foundedRating);
    }

}

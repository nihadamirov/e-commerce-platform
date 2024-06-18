package com.euphoria_ecommerce.service;

import com.euphoria_ecommerce.dto.RatingDto;
import com.euphoria_ecommerce.exception.ProductNotFoundException;
import com.euphoria_ecommerce.exception.RatingNotFoundException;
import com.euphoria_ecommerce.exception.UserNotFoundException;
import com.euphoria_ecommerce.model.Product;
import com.euphoria_ecommerce.model.Rating;
import com.euphoria_ecommerce.model.User;
import com.euphoria_ecommerce.repository.ProductRepository;
import com.euphoria_ecommerce.repository.RatingRepository;
import com.euphoria_ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RatingService {
    private final RatingRepository ratingRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public List<RatingDto> getAllRatings() {
        return ratingRepository.findAll()
                .stream().map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public RatingDto getRatingById(int id) {
        Rating rating = ratingRepository.findById(id)
                .orElseThrow(() -> new RatingNotFoundException("Rating not found with id: " + id));

        return modelMapper.map(rating, RatingDto.class);
    }

    public RatingDto createRating(RatingDto ratingDto) {
        Rating rating = new Rating();
        Optional<Rating> existRating = ratingRepository.findByUserIdAndProductId(ratingDto.getUserId(), ratingDto.getProductId());
        Product product = productRepository.findById(ratingDto.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id:" + ratingDto.getProductId()));
        User user = userRepository.findById(ratingDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + ratingDto.getUserId()));
        Rating savedRating = new Rating();

        if (existRating.isPresent()) {
            Rating foundedRating = ratingRepository.findById(existRating.get().getId())
                    .orElseThrow(() -> new RatingNotFoundException("Rating not found with id: " + ratingDto.getRating()));
            foundedRating.setRating(ratingDto.getRating());
            foundedRating.setProduct(product);
            foundedRating.setUser(user);
            savedRating = ratingRepository.save(foundedRating);
        } else {
            rating.setId(rating.getId());
            rating.setProduct(product);
            rating.setUser(user);
            rating.setRating(ratingDto.getRating());
            savedRating = ratingRepository.save(rating);
        }
        return modelMapper.map(savedRating, RatingDto.class);
    }

//    public RatingDto updateRating(int id, RatingDto ratingDto) {
//        Rating foundedRating = ratingRepository.findById(id)
//                .orElseThrow(() -> new RatingNotFoundException("Rating not found with id: " + id));
//        Product product = productRepository.findById(ratingDto.getProductId()).orElseThrow(() -> new ProductNotFoundException("Product not found with id:" + ratingDto.getProductId()));
//        User user = userRepository.findById(ratingDto.getUserId()).orElseThrow(() -> new UserNotFoundException("User not found with id: " + ratingDto.getUserId()));
//        foundedRating.setRating(ratingDto.getRating());
//        foundedRating.setProduct(product);
//        foundedRating.setUser(user);
//        return modelMapper.map(ratingRepository.save(foundedRating), RatingDto.class);
//    }

    private RatingDto convertEntityToDto(Rating rating) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        RatingDto ratingDto = new RatingDto();
        ratingDto = modelMapper.map(rating, RatingDto.class);
        return ratingDto;
    }
}

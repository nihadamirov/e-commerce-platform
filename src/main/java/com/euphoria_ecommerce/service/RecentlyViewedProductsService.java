package com.euphoria_ecommerce.service;


import com.euphoria_ecommerce.dto.RecentlyViewedProductsDto;
import com.euphoria_ecommerce.exception.ProductNotFoundException;
import com.euphoria_ecommerce.exception.UserNotFoundException;
import com.euphoria_ecommerce.exception.ViewedProductNotFoundException;
import com.euphoria_ecommerce.model.*;
import com.euphoria_ecommerce.repository.ProductRepository;
import com.euphoria_ecommerce.repository.RecentlyViewedProductsRepository;
import com.euphoria_ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecentlyViewedProductsService {
    private final RecentlyViewedProductsRepository viewedProductsRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public List<RecentlyViewedProductsDto> getAllViewedProducts() {
        return viewedProductsRepository.findAll()
                .stream().map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public RecentlyViewedProductsDto getViewedProductById(int id) {
        RecentlyViewedProducts viewedProducts = viewedProductsRepository.findById(id)
                .orElseThrow(() -> new ViewedProductNotFoundException("Viewed product not  found with id: " + id));
        return modelMapper.map(viewedProducts, RecentlyViewedProductsDto.class);
    }

    public RecentlyViewedProductsDto createViewedProduct(RecentlyViewedProductsDto viewedProductsDto) {
        RecentlyViewedProducts viewedProducts = new RecentlyViewedProducts();

        Product product = productRepository.findById(viewedProductsDto.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id:" + viewedProductsDto.getProductId()));
        User user = userRepository.findById(viewedProductsDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + viewedProductsDto.getUserId()));

        viewedProducts.setId(viewedProductsDto.getId());
        viewedProducts.setProduct(product);
        viewedProducts.setUser(user);
        viewedProducts.setViewedAt(viewedProductsDto.getViewedAt());
        return modelMapper.map(viewedProductsRepository.save(viewedProducts), RecentlyViewedProductsDto.class);
    }

    public RecentlyViewedProductsDto updateViewedProducts(int id, RecentlyViewedProductsDto viewedProductsDto) {
        RecentlyViewedProducts foundedProduct = viewedProductsRepository.findById(id)
                .orElseThrow(() -> new ViewedProductNotFoundException("Viewed product not  found with id: " + id));

        Product product = productRepository.findById(viewedProductsDto.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id:" + viewedProductsDto.getProductId()));
        User user = userRepository.findById(viewedProductsDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + viewedProductsDto.getUserId()));

        foundedProduct.setProduct(product);
        foundedProduct.setUser(user);
        foundedProduct.setViewedAt(viewedProductsDto.getViewedAt());
        return modelMapper.map(viewedProductsRepository.save(foundedProduct), RecentlyViewedProductsDto.class);

    }

    private RecentlyViewedProductsDto convertEntityToDto(RecentlyViewedProducts viewedProducts) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        RecentlyViewedProductsDto viewedProductsDto = new RecentlyViewedProductsDto();
        viewedProductsDto = modelMapper.map(viewedProducts, RecentlyViewedProductsDto.class);
        return viewedProductsDto;
    }
}

package com.euphoria_ecommerce.service;


import com.euphoria_ecommerce.model.RecentlyViewedProducts;
import com.euphoria_ecommerce.repository.RecentlyViewedProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecentlyViewedProductsService {
    private final RecentlyViewedProductsRepository viewedProductsRepository;

    public List<RecentlyViewedProducts> getAllViewedProducts() {
        return viewedProductsRepository.findAll();
    }

    public RecentlyViewedProducts getViewedProductById(int id) {
        return viewedProductsRepository.findById(id).get();
    }

    public RecentlyViewedProducts createViewedProduct(RecentlyViewedProducts viewedProducts) {
        return viewedProductsRepository.save(viewedProducts);
    }

    public RecentlyViewedProducts updateViewedProducts(int id, RecentlyViewedProducts viewedProducts) {
        RecentlyViewedProducts foundedProduct = viewedProductsRepository.findById(id).get();
        foundedProduct.setUser(viewedProducts.getUser());
        foundedProduct.setProduct(viewedProducts.getProduct());
        return viewedProductsRepository.save(foundedProduct);
    }
}

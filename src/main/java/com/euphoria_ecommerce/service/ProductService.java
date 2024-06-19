package com.euphoria_ecommerce.service;

import com.euphoria_ecommerce.model.Product;
import com.euphoria_ecommerce.repository.PriceRepository;
import com.euphoria_ecommerce.repository.ProductRepository;
import io.jsonwebtoken.lang.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    private final PriceRepository priceRepository;

    public ProductService(ProductRepository productRepository, PriceRepository priceRepository) {
        this.productRepository = productRepository;
        this.priceRepository = priceRepository;
    }

    public Product add(Product product) {
//        for (Price price : product.getPrice()) {
//            price.setProduct(product);
//        }
        if (!isSizeValid(product.getSize())) {
            throw new RuntimeException("Size can't different from XS,S,M,L,XL,XXL");
        }
        return productRepository.save(product);
    }

    private boolean isSizeValid(String size) {

        List<String> listOfSize = Arrays.asList(new String[]{"XS", "S", "XL", "M", "L", "XXL"});
        return listOfSize.contains(size);
    }

    public Product update(Product product, Long id) {
        Product product1 = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found" + id));
        product1.setProductName(product.getProductName());
        product1.setCategory(product.getCategory());
        product1.setColor(product.getColor());
        product1.setSize(product.getSize());
        product1.setPrice(product.getPrice());
        product1.setPicture(product.getPicture());

        return productRepository.save(product1);
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }
}

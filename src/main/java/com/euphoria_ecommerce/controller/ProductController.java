package com.euphoria_ecommerce.controller;

import com.euphoria_ecommerce.model.Product;
import com.euphoria_ecommerce.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product add(@RequestBody Product product) {
        return productService.add(product);
    }

    @PutMapping("{id}")
    public Product update(@RequestBody Product product, @PathVariable Long id) {
        return productService.update(product,id);
    }

    @GetMapping
    public List<Product> getAll() {
        return productService.getAll();
    }
}

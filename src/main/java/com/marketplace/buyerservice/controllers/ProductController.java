package com.marketplace.buyerservice.controllers;

import com.marketplace.buyerservice.models.Product;
import com.marketplace.buyerservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
@CrossOrigin
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping
    public Product save(@RequestBody Product product){
        return productService.save(product);
    }

    @GetMapping
    public Page<Product> get(Pageable page){
        return productService.get(page);
    }

    @GetMapping("/{productId}")
    public Product getProduct(@PathVariable String productId){
        return productService.getProduct(productId);
    }

}

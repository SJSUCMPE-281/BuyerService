package com.marketplace.buyerservice.services;

import com.marketplace.buyerservice.models.Product;
import com.marketplace.buyerservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public Product save(Product product){
        return productRepository.save(product);
    }

    public void delete(Product product){
        productRepository.delete(product);
    }
}

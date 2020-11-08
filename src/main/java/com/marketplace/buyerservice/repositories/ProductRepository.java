package com.marketplace.buyerservice.repositories;

import com.marketplace.buyerservice.models.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product,String> {
}

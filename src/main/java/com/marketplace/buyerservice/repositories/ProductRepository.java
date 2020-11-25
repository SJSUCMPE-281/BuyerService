package com.marketplace.buyerservice.repositories;

import com.marketplace.buyerservice.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product,String> {
    Page<Product> findAllByActiveFlagEquals(Pageable pageable,boolean activeFlag);
}

package com.marketplace.buyerservice.repositories;

import com.marketplace.buyerservice.models.Sale;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Sale,String> {
}

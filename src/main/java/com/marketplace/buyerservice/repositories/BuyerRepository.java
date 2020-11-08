package com.marketplace.buyerservice.repositories;

import com.marketplace.buyerservice.models.Buyer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyerRepository extends CrudRepository<Buyer, String> {

}

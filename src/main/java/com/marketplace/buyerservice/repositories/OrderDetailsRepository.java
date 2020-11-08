package com.marketplace.buyerservice.repositories;

import com.marketplace.buyerservice.models.OrderDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsRepository extends CrudRepository<OrderDetails, String> {
}

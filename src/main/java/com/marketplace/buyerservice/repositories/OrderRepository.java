package com.marketplace.buyerservice.repositories;

import com.marketplace.buyerservice.models.OrderStatus;
import com.marketplace.buyerservice.models.Sale;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Sale,String> {

    Iterable<Sale> findByBuyer_BuyerIdOrderByUpdatedAtDesc(String id);

    Iterable<Sale> findBySellerIdAndStatusIsNotOrderByUpdatedAtDesc(String sellerId , OrderStatus status);
}

package com.marketplace.buyerservice.repositories;

import com.marketplace.buyerservice.models.OrderStatus;
import com.marketplace.buyerservice.models.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface OrderRepository extends CrudRepository<Sale,String> {

    Iterable<Sale> findByBuyer_BuyerIdOrderByUpdatedAtDesc(String id);

    Iterable<Sale> findBySellerIdAndStatusIsNotOrderByUpdatedAtDesc(String sellerId , OrderStatus status);

    @Query("select count(s), sum(s.totalAmount) from Sale s where s.sellerId=:sellerId and s.createdAt>= :createdAt")
    Object findSellerBillingDetails(@Param("sellerId") String sellerId, @Param("createdAt") Date createdAt);



}

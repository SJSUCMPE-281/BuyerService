package com.marketplace.buyerservice.services;

import com.marketplace.buyerservice.models.Buyer;
import com.marketplace.buyerservice.repositories.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class BuyerService {

    @Autowired
    BuyerRepository buyerRepository;

    public Buyer save(Buyer buyer){
        buyer.setBuyerId(UUID.randomUUID().toString());
        return buyerRepository.save(buyer);
    }
}

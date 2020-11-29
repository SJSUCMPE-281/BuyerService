package com.marketplace.buyerservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.marketplace.buyerservice.models.Buyer;
import com.marketplace.buyerservice.repositories.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyerService {

    @Autowired
    BuyerRepository buyerRepository;

    @Autowired
    PublisherClient publisherClient;

    public Buyer save(Buyer buyer) throws JsonProcessingException {
        try {
            return buyerRepository.findById(buyer.getBuyerId()).get();
        } catch (Exception e){
            Buyer newBuyer = buyerRepository.save(buyer);
            publisherClient.publishUserRegistrationEvent(newBuyer);
            return newBuyer;
        }
    }
}

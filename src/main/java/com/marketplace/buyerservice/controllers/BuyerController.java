package com.marketplace.buyerservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.marketplace.buyerservice.models.Buyer;
import com.marketplace.buyerservice.services.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/buyer")
public class BuyerController  {
    @Autowired
    BuyerService buyerService;

    @PostMapping
    public Buyer save(@RequestBody Buyer buyer) throws JsonProcessingException {
       return  buyerService.save(buyer);
    }
}

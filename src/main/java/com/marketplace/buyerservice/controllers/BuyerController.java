package com.marketplace.buyerservice.controllers;

import com.marketplace.buyerservice.models.Buyer;
import com.marketplace.buyerservice.services.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/buyer")
public class BuyerController  {
    @Autowired
    BuyerService buyerService;

    @PostMapping
    public Buyer save(@RequestBody Buyer buyer){
       return  buyerService.save(buyer);
    }
}

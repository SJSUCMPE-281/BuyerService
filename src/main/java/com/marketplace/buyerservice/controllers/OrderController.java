package com.marketplace.buyerservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.marketplace.buyerservice.models.Sale;
import com.marketplace.buyerservice.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/buyer/{buyerId}/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping
    public Sale save(@PathVariable String buyerId, @RequestBody Sale sale) throws JsonProcessingException {
        return orderService.save(sale,buyerId);
    }

    @GetMapping("/{orderId}")
    public Sale getOrder(@PathVariable String orderId){
        return orderService.get(orderId);
    }

    @GetMapping
    public Iterable<Sale> getAll(@PathVariable String buyerId){
        return orderService.getAll(buyerId);
    }

}

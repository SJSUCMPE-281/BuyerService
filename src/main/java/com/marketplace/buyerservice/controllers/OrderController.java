package com.marketplace.buyerservice.controllers;

import com.marketplace.buyerservice.models.Sale;
import com.marketplace.buyerservice.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/buyer/{buyerId}/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping
    public Sale save(@PathVariable String buyerId, @RequestBody Sale sale){
        return orderService.save(sale,buyerId);
    }

    @GetMapping("/{orderId}")
    public Sale getOrder(@PathVariable String orderId){
        return orderService.get(orderId);
    }
}
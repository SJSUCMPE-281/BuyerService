package com.marketplace.buyerservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.marketplace.buyerservice.models.BillingData;
import com.marketplace.buyerservice.models.OrderStatus;
import com.marketplace.buyerservice.models.Sale;
import com.marketplace.buyerservice.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seller/{sellerId}")
@CrossOrigin
public class SellerContoller {
    @Autowired
    OrderService orderService;

    @GetMapping("/order/{orderStatus}")
    public Iterable<Sale> getSellerOrders(@PathVariable String sellerId, @PathVariable OrderStatus orderStatus){
        if(orderStatus.equals(OrderStatus.SHIPPED)){
            return orderService.getCompletedOrders(sellerId);
        }else{
            return orderService.getOpenOrders(sellerId);
        }
    }
    @PutMapping("/order")
    public Sale update(@PathVariable String sellerId, @RequestBody Sale sale) throws JsonProcessingException {
        return orderService.update(sale);
    }

    @GetMapping("/billing")
    public BillingData getBillingData(@PathVariable String sellerId){
        return orderService.getSellerBillingData(sellerId);
    }
}

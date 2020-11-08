package com.marketplace.buyerservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.marketplace.buyerservice.models.Cart;
import com.marketplace.buyerservice.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/{buyerId}/cart")
public class CartController {
    @Autowired
    CartService cartService;

    @PostMapping
    public Cart saveCart(@PathVariable String buyerId, @RequestBody Cart cart) throws JsonProcessingException {
        cart.setBuyerId(buyerId);
        cartService.save(cart);
        return cart;
    }

    @GetMapping()
    public Cart getCart(@PathVariable String buyerId) throws JsonProcessingException {
        return cartService.get(buyerId);
    }

    @DeleteMapping
    public void deleteCart(@PathVariable String buyerId){
        cartService.delete(buyerId);
    }
}

package com.marketplace.buyerservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.marketplace.buyerservice.models.Cart;
import com.marketplace.buyerservice.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

    public void save(Cart cart) throws JsonProcessingException {
        cart.setCartId(UUID.randomUUID().toString());
        cartRepository.add(cart);
    }

    public Cart get(String buyerId) throws JsonProcessingException {
        return cartRepository.findCart(buyerId);
    }

    public void delete(String buyerId){
        cartRepository.delete(buyerId);
    }
}

package com.marketplace.buyerservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.marketplace.buyerservice.models.Cart;
import com.marketplace.buyerservice.models.OrderDetails;
import com.marketplace.buyerservice.models.Product;
import com.marketplace.buyerservice.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.UUID;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

    public void save(Cart cart) throws JsonProcessingException {
        List<OrderDetails> orderDetailsList = cart.getOrderDetails();
        Map<Product, Integer> map = new HashMap<>();
        for(OrderDetails orderDetails: orderDetailsList){
            if(map.containsKey(orderDetails.getProduct())){
                map.put(orderDetails.getProduct(),map.get(orderDetails.getProduct())+1);
            }else{
                map.put(orderDetails.getProduct(),orderDetails.getQuantity());
            }
        }
        List<OrderDetails> newOrderDetailsList = new ArrayList<>();
        Set<OrderDetails> set = new HashSet<>();
        for(OrderDetails orderDetails: orderDetailsList) {
            orderDetails.setQuantity(map.get(orderDetails.getProduct()));
            set.add(orderDetails);
        }
        newOrderDetailsList.addAll(set);
        cart.setOrderDetails(newOrderDetailsList);
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

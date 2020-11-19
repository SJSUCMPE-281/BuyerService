package com.marketplace.buyerservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.marketplace.buyerservice.models.Cart;
import com.marketplace.buyerservice.models.OrderDetails;
import com.marketplace.buyerservice.models.Product;
import com.marketplace.buyerservice.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

    public void save(Cart cart) throws JsonProcessingException {
        List<OrderDetails> orderDetailsList = cart.getOrderDetails();
        Map<String, Integer> map = new HashMap<>();
        Map<String, Product> productMap = new HashMap<>();
        for(OrderDetails orderDetails: orderDetailsList){
            if(map.containsKey(orderDetails.getProduct().getProductId())){
                map.put(orderDetails.getProduct().getProductId(),map.get(orderDetails.getProduct().getProductId())+1);
            }else{
                map.put(orderDetails.getProduct().getProductId(),orderDetails.getQuantity());
            }
            productMap.put(orderDetails.getProduct().getProductId(),orderDetails.getProduct());
        }
        List<OrderDetails> newOrderDetailsList = new ArrayList<>();
        //Set<OrderDetails> set = new HashSet<>();
        BigDecimal totalPrice = BigDecimal.ZERO;
        BigDecimal taxAmount = BigDecimal.ZERO;
        BigDecimal totalAmount = BigDecimal.ZERO;
        for(String key: map.keySet()){
            OrderDetails newOrderDetails = new OrderDetails();
            newOrderDetails.setQuantity(map.get(key));
            newOrderDetails.setProduct(productMap.get(key));
            newOrderDetails.setOrderDetailAmount(newOrderDetails.getProduct().getPrice());
            newOrderDetailsList.add(newOrderDetails);
            totalPrice = totalPrice.add(newOrderDetails.getProduct().getPrice().multiply(new BigDecimal(newOrderDetails.getQuantity())));
        }
        cart.setTotalPrice(totalPrice);
        taxAmount = totalPrice.multiply(new BigDecimal(5)).divide(new BigDecimal(100));
        totalAmount = taxAmount.add(cart.getTotalPrice());
        cart.setTaxAmount(taxAmount);
        cart.setTotalAmount(totalAmount);
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

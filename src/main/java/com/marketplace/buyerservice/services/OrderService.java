package com.marketplace.buyerservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.marketplace.buyerservice.models.Buyer;
import com.marketplace.buyerservice.models.Product;
import com.marketplace.buyerservice.models.Sale;
import com.marketplace.buyerservice.models.OrderDetails;
import com.marketplace.buyerservice.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    BuyerRepository buyerRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Autowired
    PublisherClient publisherClient;

    @Transactional
    public Sale save(Sale sale, String buyerId) throws JsonProcessingException {
        Buyer buyer = buyerRepository.findById(buyerId).get();
        sale.setOrderId(UUID.randomUUID().toString());
        sale.setBuyer(buyer);
        sale.getAddress().setAddressId(UUID.randomUUID().toString());
        sale.setAddress(addressRepository.save(sale.getAddress()));
        List<OrderDetails> orderDetailsList = new ArrayList<>();

        for(OrderDetails details: sale.getOrderDetails()) {
            Product product = productRepository.findById(details.getProduct().getProductId()).get();
            details.setProduct(product);
            details.setOrderDetailsId(UUID.randomUUID().toString());
            orderDetailsList.add(orderDetailsRepository.save(details));
        }
        sale.setOrderDetails(orderDetailsList);
        Sale newSale = orderRepository.save(sale);
        publisherClient.publishOrderEvent(newSale);
        return newSale;
    }

    public Sale get(String orderId){
        return orderRepository.findById(orderId).get();
    }
}

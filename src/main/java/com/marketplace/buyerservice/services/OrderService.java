package com.marketplace.buyerservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.marketplace.buyerservice.models.*;
import com.marketplace.buyerservice.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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

    public Iterable<Sale> getAll(String buyerId){
        return orderRepository.findByBuyer_BuyerIdOrderByUpdatedAtDesc(buyerId);
    }

    public Iterable<Sale> getOpenOrders(String sellerId){
        return orderRepository.findBySellerIdAndStatusIsNotOrderByUpdatedAtDesc(sellerId,OrderStatus.SHIPPED);
    }

    public Iterable<Sale> getCompletedOrders(String sellerId){
        return orderRepository.findBySellerIdAndStatusIsNotOrderByUpdatedAtDesc(sellerId,OrderStatus.ORDERED);
    }

    public Sale update(Sale sale) throws JsonProcessingException {
        Sale oldOrder = orderRepository.findById(sale.getOrderId()).get();
        if(sale.getTrackingId() != null){
            oldOrder.setTrackingId(sale.getTrackingId());
        }
        oldOrder.setStatus(OrderStatus.SHIPPED);
        Sale newSale = orderRepository.save(oldOrder);
        publisherClient.publishOrderEvent(newSale);
        return newSale;
    }
    public BillingData getSellerBillingData(String sellerId){
        LocalDateTime date = LocalDateTime.now().minusDays(30);
        Date newDate = Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
        Object object[] =(Object[]) orderRepository.findSellerBillingDetails(sellerId,newDate);
        BillingData billingData = new BillingData();
        billingData.setSaleAmount((BigDecimal) object[1]);
        billingData.setSaleCount((Long)object[0]);
        return billingData;
    }
}

package com.marketplace.buyerservice.listeners;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marketplace.buyerservice.models.EventType;
import com.marketplace.buyerservice.models.Product;
import com.marketplace.buyerservice.models.SNSMessage;
import com.marketplace.buyerservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class SQSListener {
    @Autowired
    ProductService productService;

    @Autowired
    ObjectMapper objectMapper;

    static final String QUEUE_NAME = "marketplace-product-queue";

    @SqsListener(QUEUE_NAME)
    public void receiveMessage(String message) throws JsonProcessingException {
        SNSMessage snsMessage = objectMapper.readValue(message,SNSMessage.class);
        EventType event = snsMessage.getEventType();
        Product product = snsMessage.getProductDTO();
        if(event.name().equals("ENTITY_DELETE")) {
            Product productRO = productService.getProduct(product.getProductId());
            productRO.setActiveFlag(false);
            productService.save(productRO);
        } else {
            productService.save(product);
        }
    }
}

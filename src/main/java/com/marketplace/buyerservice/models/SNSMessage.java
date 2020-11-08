package com.marketplace.buyerservice.models;

import lombok.Data;

@Data
public class SNSMessage {
    EventType eventType;
    Product productDTO;
}

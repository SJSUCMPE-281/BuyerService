package com.marketplace.buyerservice.models;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderDetailsDTO {
    String OrderDetailsId;
    String productId;
    String productName;
    String productDescription;
    BigDecimal price;
    String sellerId;
    String shopName;
    String imageUrl;
    String category;
    Date createdAt;
    Date updatedAt;
    int quantity;
    BigDecimal orderDetailAmount;
}

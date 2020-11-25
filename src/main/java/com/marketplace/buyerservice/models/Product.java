package com.marketplace.buyerservice.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
public class Product {
    @Id
    String productId;
    String productName;
    String productDescription;
    BigDecimal price;
    String sellerId;
    String shopName;
    String imageUrl;
    String category;
    int reviewCount;
    boolean activeFlag=true;
    Double rating;
    Date createdAt;
    Date updatedAt;
}

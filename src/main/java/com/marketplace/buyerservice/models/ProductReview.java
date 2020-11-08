package com.marketplace.buyerservice.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class ProductReview {
    @Id
    String reviewId;
    int rating;
    String comment;
    String buyerId;
}

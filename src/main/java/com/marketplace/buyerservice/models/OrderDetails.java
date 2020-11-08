package com.marketplace.buyerservice.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Entity
@Data
public class OrderDetails {
    @Id
    String OrderDetailsId;
    @OneToOne
    Product product;
    int quantity;
    BigDecimal orderDetailAmount;

}

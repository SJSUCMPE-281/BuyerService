package com.marketplace.buyerservice.models;

import lombok.Data;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.List;

@Data
public class Cart {
    @Id
    String cartId;
    String buyerId;
    List<OrderDetails> orderDetails;
    BigDecimal totalPrice;
    BigDecimal taxAmount;
    BigDecimal totalAmount;
}

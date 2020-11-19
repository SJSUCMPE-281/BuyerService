package com.marketplace.buyerservice.models;

import lombok.Data;

import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class SaleDTO {
    String orderId;
    @OneToMany
    List<OrderDetailsDTO> orderDetailsDTO;
    BigDecimal totalPrice;
    BigDecimal taxAmount;
    BigDecimal totalAmount;
    String status;
    String trackingId;
    String buyerId;
    Date createdAt;
    Date updatedAt;
}

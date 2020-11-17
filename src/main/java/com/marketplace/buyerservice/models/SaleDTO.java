package com.marketplace.buyerservice.models;

import lombok.Data;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
    @OneToOne
    UserDTO buyer;
    Date createdAt;
    Date updatedAt;
}

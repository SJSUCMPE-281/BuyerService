package com.marketplace.buyerservice.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Sale {

    @Id
    String orderId;
    @OneToMany
    List<OrderDetails> orderDetails;
    @OneToOne
    Address address;
    BigDecimal totalPrice;
    BigDecimal taxAmount;
    BigDecimal totalAmount;
    OrderStatus status;
    String trackingId;
    @OneToOne
    Buyer buyer;
    @CreatedDate
    Date createdAt;
    @LastModifiedDate
    Date updatedAt;

}

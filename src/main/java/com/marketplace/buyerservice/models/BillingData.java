package com.marketplace.buyerservice.models;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BillingData {
    Long saleCount;
    BigDecimal saleAmount;
}

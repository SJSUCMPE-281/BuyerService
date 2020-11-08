package com.marketplace.buyerservice.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Address {
    @Id
    String addressId;
    String street1;
    String street2;
    String city;
    String state;
    String zip;
}

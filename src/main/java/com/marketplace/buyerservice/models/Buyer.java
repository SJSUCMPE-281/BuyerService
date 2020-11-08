package com.marketplace.buyerservice.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Buyer {
    @Id
    String buyerId;
    String firstName;
    String lastName;
    String phoneNumber;
    String email;
    @CreatedDate
    Date createdAt;
    @LastModifiedDate
    Date updatedAt;
}

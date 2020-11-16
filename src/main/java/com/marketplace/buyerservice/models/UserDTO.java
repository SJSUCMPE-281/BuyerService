package com.marketplace.buyerservice.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class UserDTO {
    @Id
    String userId;
    String firstName;
    String lastName;
    String phoneNumber;
    String email;
    String role;
    Date createdAt;
    Date updatedAt;

}

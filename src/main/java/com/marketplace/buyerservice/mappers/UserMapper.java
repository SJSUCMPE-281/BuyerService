package com.marketplace.buyerservice.mappers;

import com.marketplace.buyerservice.models.Buyer;
import com.marketplace.buyerservice.models.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO toDTO(Buyer buyer){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(buyer.getBuyerId());
        userDTO.setFirstName(buyer.getFirstName());
        userDTO.setLastName(buyer.getLastName());
        userDTO.setEmail(buyer.getEmail());
        userDTO.setPhoneNumber(buyer.getPhoneNumber());
        userDTO.setRole("Buyer");
        userDTO.setCreatedAt(buyer.getCreatedAt());
        userDTO.setUpdatedAt(buyer.getUpdatedAt());
        return userDTO;
    }
}

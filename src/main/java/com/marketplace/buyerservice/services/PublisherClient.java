package com.marketplace.buyerservice.services;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marketplace.buyerservice.mappers.UserMapper;
import com.marketplace.buyerservice.models.Buyer;
import com.marketplace.buyerservice.models.UserDTO;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Data
public class PublisherClient {
    @Value("${cloud.aws.region.static}")
    private String awsRegion;

    @Value("${amazon.user.sns.topic}")
    private String userSnsTopicARN;

    private AmazonSNS amazonSNS;

    @Autowired
    UserMapper userMapper;

    @PostConstruct
    private void init(){
        amazonSNS = AmazonSNSClientBuilder.standard().withRegion(awsRegion).build();
    }

    public void publishUserRegistrationEvent(Buyer buyer) throws JsonProcessingException {
        UserDTO userDTO = userMapper.toDTO(buyer);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = objectMapper.writeValueAsString(userDTO);
        amazonSNS.publish(userSnsTopicARN,jsonPayload);
    }
}

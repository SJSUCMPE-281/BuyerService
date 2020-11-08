package com.marketplace.buyerservice.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marketplace.buyerservice.models.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
public class CartRepository {
    private static final String KEY = "Cart";
    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations hashOperations;
    private final ObjectMapper objectMapper;

    @Autowired
    public CartRepository(RedisTemplate<String, Object> redisTemplate, ObjectMapper objectMapper){
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    private void init(){
        hashOperations = redisTemplate.opsForHash();
    }

    public void add(final Cart cart) throws JsonProcessingException {
        hashOperations.put(KEY, cart.getBuyerId(), objectMapper.writeValueAsString(cart));
    }

    public Cart findCart(final String id) throws JsonProcessingException {
        Object value =  hashOperations.get(KEY, id);
        if(value == null) return null;
        Cart cart = objectMapper.readValue((String) value, Cart.class);
        return cart;
    }

    public void delete(final String id) {
        hashOperations.delete(KEY, id);
    }
}

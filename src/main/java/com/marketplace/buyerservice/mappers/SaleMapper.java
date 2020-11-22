package com.marketplace.buyerservice.mappers;

import com.marketplace.buyerservice.models.OrderDetails;
import com.marketplace.buyerservice.models.OrderDetailsDTO;
import com.marketplace.buyerservice.models.Sale;
import com.marketplace.buyerservice.models.SaleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SaleMapper {
    @Autowired
    UserMapper userMapper;

    public SaleDTO toDTO(Sale sale){
        SaleDTO saleDTO = new SaleDTO();
        saleDTO.setOrderId(sale.getOrderId());
        List<OrderDetails> orderDetailsList = sale.getOrderDetails();
        List<OrderDetailsDTO> orderDetailsDTOList = new ArrayList<>();
        for(OrderDetails orderDetails:orderDetailsList){
            OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();
            orderDetailsDTO.setOrderDetailsId(orderDetails.getOrderDetailsId());
            orderDetailsDTO.setProductId(orderDetails.getProduct().getProductId());
            orderDetailsDTO.setProductName(orderDetails.getProduct().getProductName());
            orderDetailsDTO.setProductDescription(orderDetails.getProduct().getProductDescription());
            orderDetailsDTO.setPrice(orderDetails.getProduct().getPrice());
            orderDetailsDTO.setSellerId(orderDetails.getProduct().getSellerId());
            orderDetailsDTO.setShopName(orderDetails.getProduct().getShopName());
            orderDetailsDTO.setImageUrl(orderDetails.getProduct().getImageUrl());
            orderDetailsDTO.setCategory(orderDetails.getProduct().getCategory());
            orderDetailsDTO.setCreatedAt(orderDetails.getProduct().getCreatedAt());
            orderDetailsDTO.setUpdatedAt(orderDetails.getProduct().getUpdatedAt());
            orderDetailsDTO.setQuantity(orderDetails.getQuantity());
            orderDetailsDTO.setOrderDetailAmount(orderDetails.getOrderDetailAmount());
            orderDetailsDTOList.add(orderDetailsDTO);
        }
        saleDTO.setOrderDetailsDTO(orderDetailsDTOList);
        saleDTO.setTotalPrice(sale.getTotalPrice());
        saleDTO.setTaxAmount(sale.getTaxAmount());
        saleDTO.setTotalAmount(sale.getTotalAmount());
        saleDTO.setStatus("PLACED");
        saleDTO.setTrackingId(sale.getTrackingId());
        saleDTO.setBuyerId(sale.getBuyer().getBuyerId());
        saleDTO.setAddress1(sale.getAddress().getStreet1());
        saleDTO.setAddress2(sale.getAddress().getStreet2());
        saleDTO.setCity(sale.getAddress().getCity());
        saleDTO.setState(sale.getAddress().getState());
        saleDTO.setZip(sale.getAddress().getZip());
        saleDTO.setCreatedAt(sale.getCreatedAt());
        saleDTO.setUpdatedAt(sale.getUpdatedAt());
        return saleDTO;
    }
}

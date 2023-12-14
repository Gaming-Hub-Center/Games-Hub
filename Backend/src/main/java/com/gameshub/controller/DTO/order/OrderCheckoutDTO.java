package com.gameshub.controller.DTO.order;

import lombok.*;

@Data
public class OrderCheckoutDTO {
    int buyerID;
    String paymentMethod;
}

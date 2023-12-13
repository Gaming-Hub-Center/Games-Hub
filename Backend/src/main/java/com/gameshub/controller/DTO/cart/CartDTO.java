package com.gameshub.controller.DTO.cart;
import lombok.*;

@Data
public class CartDTO {
    private int buyerID;
    private int productID;
    private int count;
}

package com.gameshub.utils;

import com.gameshub.model.cart.CartDAO;
import com.gameshub.model.cart.DigitalCartDAO;
import com.gameshub.model.cart.PhysicalCartDAO;
import com.gameshub.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartItemsPrinterRunner implements CommandLineRunner {

    private final CartService cartService;

    @Autowired
    public CartItemsPrinterRunner(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public void run(String... args) {
        int buyerId = 2; // Replace with actual buyer ID if needed
        int productId = 1; // Replace with actual product ID if needed
        int count = 2; // Replace with desired count

        // Add an item to the physical cart

        cartService.addOrUpdateDigitalCartItem(buyerId,productId,count);

//        List<PhysicalCartDAO> cartItems = cartService.getPhysicalCartItems(1); // Replace with actual buyer ID if needed
//
//        //cartService.removePhysicalCartItem(buyerId,productId);
//
//        System.out.println("Physical Cart Items:");
//        for (PhysicalCartDAO item : cartItems) {
//            System.out.println("buyer id = "+item.getId().getBuyerID());
//            System.out.println("product id = "+item.getId().getProductID());
//            System.out.println("item count= "+item.getCount());
//        }
    }
}

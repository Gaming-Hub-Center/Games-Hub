package com.gameshub.utils;

import com.gameshub.model.cart.CartDAO;
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
        int count = 500; // Replace with desired count

        // Add an item to the physical cart
        CartDAO.CartKey newCartItemKey = new CartDAO.CartKey(buyerId, productId);
        PhysicalCartDAO newCartItem = new PhysicalCartDAO(newCartItemKey, count, null, null);
        cartService.addOrUpdatePhysicalCartItem(newCartItem);

//        List<PhysicalCartDAO> cartItems = cartService.getPhysicalCartItems(2); // Replace with actual buyer ID if needed
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

package com.gameshub.service;

import com.gameshub.model.cart.*;
//import com.gameshub.model.cart.DigitalCartDAO;
//import com.gameshub.repository.DigitalCartRepository;
//import com.gameshub.repository.PhysicalCartRepository;
import com.gameshub.repository.cart.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CartService {

    private final PhysicalCartRepository physicalCartRepository;
    private final DigitalCartRepository digitalCartRepository;



    // Add or update a physical cart item
    @Transactional
    public void addOrUpdatePhysicalCartItem(int buyerID, int productID, int count) {
        CartDAO.CartKey newCartItemKey = new CartDAO.CartKey(buyerID, productID);
        PhysicalCartDAO newCartItem = new PhysicalCartDAO(newCartItemKey, count);
        physicalCartRepository.save(newCartItem);
    }

    // Add or update a digital cart item
    @Transactional
    public void addOrUpdateDigitalCartItem(int buyerID, int productID, int count) {
        CartDAO.CartKey newCartItemKey = new CartDAO.CartKey(buyerID, productID);
        DigitalCartDAO newCartItem = new DigitalCartDAO(newCartItemKey, count);
        digitalCartRepository.save(newCartItem);
    }

    // Remove a physical cart item
    @Transactional
    public void removePhysicalCartItem(int buyerId, int productId) {
        PhysicalCartDAO.CartKey cartKey = new PhysicalCartDAO.CartKey(buyerId, productId);
        physicalCartRepository.deleteById(cartKey);
    }

    // Remove a digital cart item
    @Transactional
    public void removeDigitalCartItem(int buyerId, int productId) {
        DigitalCartDAO.CartKey cartKey = new DigitalCartDAO.CartKey(buyerId, productId);
        digitalCartRepository.deleteById(cartKey);
    }

    // Get all physical cart items for a buyer
    public List<PhysicalCartDAO> getPhysicalCartItems(int buyerId) {
        return physicalCartRepository.findByBuyerId(buyerId);
    }

    // Get all digital cart items for a buyer
    public List<DigitalCartDAO> getDigitalCartItems(int buyerId) {
        return digitalCartRepository.findByBuyerId(buyerId);
    }

    // Additional functionalities can be added as required.
}
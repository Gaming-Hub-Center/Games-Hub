package com.gameshub.service.cart;

<<<<<<< Updated upstream
import com.gameshub.model.cart.*;
import com.gameshub.repository.cart.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
=======
import com.gameshub.model.cart.CartDAO;
import com.gameshub.model.cart.DigitalCartDAO;
import com.gameshub.model.cart.PhysicalCartDAO;
import com.gameshub.repository.cart.DigitalCartRepository;
import com.gameshub.repository.cart.PhysicalCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
>>>>>>> Stashed changes

import java.util.List;

@RequiredArgsConstructor
@Service
public class CartService {

    private final PhysicalCartRepository physicalCartRepository;
    private final DigitalCartRepository digitalCartRepository;

    @Transactional
    public void addOrUpdatePhysicalCartItem(int buyerId, int productId, int changeCount) {
        CartDAO.CartKey cartKey = new CartDAO.CartKey(buyerId, productId);
        PhysicalCartDAO cartItem = physicalCartRepository.findById(cartKey).orElse(null);
        if (cartItem == null)
            cartItem = new PhysicalCartDAO(buyerId, productId, 0);
        cartItem.setCount(cartItem.getCount() + changeCount);
        if (cartItem.getCount() == 0)
            physicalCartRepository.delete(cartItem);
        physicalCartRepository.save(cartItem);
    }

    @Transactional
    public void addOrUpdateDigitalCartItem(int buyerId, int productId, int changeCount) {
        CartDAO.CartKey cartKey = new CartDAO.CartKey(buyerId, productId);
        DigitalCartDAO cartItem = digitalCartRepository.findById(cartKey).orElse(null);
        if (cartItem == null)
            cartItem = new DigitalCartDAO(buyerId, productId, 0);
        cartItem.setCount(cartItem.getCount() + changeCount);
        if (cartItem.getCount() == 0)
            digitalCartRepository.delete(cartItem);
        digitalCartRepository.save(cartItem);
    }

    @Transactional
    public void removePhysicalCartItem(int buyerId, int productId) {
        PhysicalCartDAO.CartKey cartKey = new PhysicalCartDAO.CartKey(buyerId, productId);
        physicalCartRepository.deleteById(cartKey);
    }

    @Transactional
    public void removeDigitalCartItem(int buyerId, int productId) {
        DigitalCartDAO.CartKey cartKey = new DigitalCartDAO.CartKey(buyerId, productId);
        digitalCartRepository.deleteById(cartKey);
    }

    public List<PhysicalCartDAO> getPhysicalCartItems(int buyerId) {
        return physicalCartRepository.findById_BuyerId(buyerId);
    }

    public List<DigitalCartDAO> getDigitalCartItems(int buyerId) {
        return digitalCartRepository.findById_BuyerId(buyerId);
    }

<<<<<<< Updated upstream
=======
    public void deletePhysicalCartItems(int buyerId) {
        physicalCartRepository.deleteById_BuyerId(buyerId);
    }

    public void deleteDigitalCartItems(int buyerId) {
        digitalCartRepository.deleteById_BuyerId(buyerId);
    }

>>>>>>> Stashed changes
}

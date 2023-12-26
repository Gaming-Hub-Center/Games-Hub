package com.gameshub.service.wishlist;

import com.gameshub.model.wishlist.DigitalWishlistDAO;
import com.gameshub.model.wishlist.PhysicalWishlistDAO;
import com.gameshub.repository.wishlist.DigitalWishlistRepository;
import com.gameshub.repository.wishlist.PhysicalWishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class wishlistService {

    private final DigitalWishlistRepository digitalWishlistRepository;
    private final PhysicalWishlistRepository physicalWishlistRepository;

    @Transactional
    public void addPhysicalWishlistProduct(int buyerID, int productID) {
        PhysicalWishlistDAO.WishlistKey newWishlistItemKey = new PhysicalWishlistDAO.WishlistKey(buyerID, productID);
        PhysicalWishlistDAO newWishlistItem = new PhysicalWishlistDAO(newWishlistItemKey);
        physicalWishlistRepository.save(newWishlistItem);
    }

    @Transactional
    public void addDigitalWishlistProduct(int buyerID, int productID) {
        DigitalWishlistDAO.WishlistKey newWishlistItemKey = new DigitalWishlistDAO.WishlistKey(buyerID, productID);
        DigitalWishlistDAO newWishlistItem = new DigitalWishlistDAO(newWishlistItemKey);
        digitalWishlistRepository.save(newWishlistItem);
    }

    @Transactional
    public void deletePhysicalWishlistProduct(int buyerID, int productID) {
        PhysicalWishlistDAO.WishlistKey wishlistKey = new PhysicalWishlistDAO.WishlistKey(buyerID, productID);
        physicalWishlistRepository.deleteById(wishlistKey);
    }

    @Transactional
    public void deleteDigitalWishlistProduct(int buyerID, int productID) {
        DigitalWishlistDAO.WishlistKey wishlistKey = new DigitalWishlistDAO.WishlistKey(buyerID, productID);
        digitalWishlistRepository.deleteById(wishlistKey);
    }

    @Transactional
    public List<PhysicalWishlistDAO> getPhysicalWishlistItems(int buyerId) {
        return physicalWishlistRepository.findByBuyerId(buyerId);
    }

    @Transactional
    public List<DigitalWishlistDAO> getDigitalWishlistItems(int buyerId) {
        return digitalWishlistRepository.findByBuyerId(buyerId);
    }
}

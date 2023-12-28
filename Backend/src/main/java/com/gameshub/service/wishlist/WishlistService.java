package com.gameshub.service.wishlist;

import com.gameshub.model.wishlist.DigitalWishlistDAO;
import com.gameshub.model.wishlist.PhysicalWishlistDAO;
import com.gameshub.repository.wishlist.DigitalWishlistRepository;
import com.gameshub.repository.wishlist.PhysicalWishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class WishlistService {

    private final DigitalWishlistRepository digitalWishlistRepository;
    private final PhysicalWishlistRepository physicalWishlistRepository;

    @Transactional
    public void addPhysicalWishlistProduct(int buyerID, int productID) {
        PhysicalWishlistDAO.WishlistKey wishlistItemKey = new PhysicalWishlistDAO.WishlistKey(buyerID, productID);
        // Check if the item already exists in the wishlist
        if (physicalWishlistRepository.existsById(wishlistItemKey)) {
            throw new DuplicateKeyException("Product already exists in the wishlist");
        } else {
            PhysicalWishlistDAO newWishlistItem = new PhysicalWishlistDAO(wishlistItemKey);
            physicalWishlistRepository.save(newWishlistItem);
        }
    }

    @Transactional
    public void addDigitalWishlistProduct(int buyerID, int productID) {
        DigitalWishlistDAO.WishlistKey newWishlistItemKey = new DigitalWishlistDAO.WishlistKey(buyerID, productID);
        // Check if the item already exists in the wishlist
        if (digitalWishlistRepository.existsById(newWishlistItemKey)) {
            throw new DuplicateKeyException("Product already exists in the wishlist");
        } else {
            DigitalWishlistDAO newWishlistItem = new DigitalWishlistDAO(newWishlistItemKey);
            digitalWishlistRepository.save(newWishlistItem);
        }
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

    public List<PhysicalWishlistDAO> getPhysicalWishlistItems(int buyerId) {
        return physicalWishlistRepository.findByBuyerId(buyerId);
    }

    public List<DigitalWishlistDAO> getDigitalWishlistItems(int buyerId) {
        return digitalWishlistRepository.findByBuyerId(buyerId);
    }
}

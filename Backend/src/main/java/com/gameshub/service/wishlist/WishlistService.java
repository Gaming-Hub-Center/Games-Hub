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
    public void addPhysicalWishlistProduct(int buyerId, int productId) {
        PhysicalWishlistDAO.WishlistKey wishlistItemKey = new PhysicalWishlistDAO.WishlistKey(buyerId, productId);
        if (physicalWishlistRepository.existsById(wishlistItemKey))
            throw new DuplicateKeyException("Product already exists in the wishlist");

        PhysicalWishlistDAO newWishlistItem = new PhysicalWishlistDAO(buyerId, productId);
        physicalWishlistRepository.save(newWishlistItem);
    }

    @Transactional
    public void addDigitalWishlistProduct(int buyerId, int productId) {
        DigitalWishlistDAO.WishlistKey newWishlistItemKey = new DigitalWishlistDAO.WishlistKey(buyerId, productId);
        if (digitalWishlistRepository.existsById(newWishlistItemKey))
            throw new DuplicateKeyException("Product already exists in the wishlist");

        DigitalWishlistDAO newWishlistItem = new DigitalWishlistDAO(buyerId, productId);
        digitalWishlistRepository.save(newWishlistItem);
    }

    @Transactional
    public void deletePhysicalWishlistProduct(int buyerId, int productId) {
        PhysicalWishlistDAO.WishlistKey wishlistKey = new PhysicalWishlistDAO.WishlistKey(buyerId, productId);
        physicalWishlistRepository.deleteById(wishlistKey);
    }

    @Transactional
    public void deleteDigitalWishlistProduct(int buyerId, int productId) {
        DigitalWishlistDAO.WishlistKey wishlistKey = new DigitalWishlistDAO.WishlistKey(buyerId, productId);
        digitalWishlistRepository.deleteById(wishlistKey);
    }

    public List<PhysicalWishlistDAO> getPhysicalWishlistItems(int buyerId) {
        return physicalWishlistRepository.findById_BuyerId(buyerId);
    }

    public List<DigitalWishlistDAO> getDigitalWishlistItems(int buyerId) {
        return digitalWishlistRepository.findById_BuyerId(buyerId);
    }
}

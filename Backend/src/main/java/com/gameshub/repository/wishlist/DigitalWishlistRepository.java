package com.gameshub.repository.wishlist;

import com.gameshub.model.wishlist.DigitalWishlistDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DigitalWishlistRepository extends JpaRepository<DigitalWishlistDAO, DigitalWishlistDAO.WishlistKey> {
    List<DigitalWishlistDAO> findById_BuyerId(int buyerId);
}

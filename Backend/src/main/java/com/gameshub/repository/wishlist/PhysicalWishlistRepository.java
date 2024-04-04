package com.gameshub.repository.wishlist;

import com.gameshub.model.wishlist.PhysicalWishlistDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhysicalWishlistRepository extends JpaRepository<PhysicalWishlistDAO, PhysicalWishlistDAO.WishlistKey> {
    List<PhysicalWishlistDAO> findById_BuyerId(int buyerId);
}

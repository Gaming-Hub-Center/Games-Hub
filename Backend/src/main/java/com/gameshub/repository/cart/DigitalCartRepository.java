package com.gameshub.repository.cart;

<<<<<<< Updated upstream
import com.gameshub.model.cart.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
=======
import com.gameshub.model.cart.DigitalCartDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
>>>>>>> Stashed changes

import java.util.List;

@Repository
public interface DigitalCartRepository extends JpaRepository<DigitalCartDAO, DigitalCartDAO.CartKey> {
    List<DigitalCartDAO> findById_BuyerId(int buyerId);
    void deleteById(DigitalCartDAO.CartKey cartKey);
<<<<<<< Updated upstream
=======
    void deleteById_BuyerId(int buyerId);
>>>>>>> Stashed changes
}
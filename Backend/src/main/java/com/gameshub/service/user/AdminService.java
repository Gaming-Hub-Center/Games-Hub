package com.gameshub.service.user;

import com.gameshub.controller.DTO.user.BuyerDTO;
import com.gameshub.controller.DTO.user.SellerDTO;
import com.gameshub.model.user.BuyerDAO;
import com.gameshub.model.user.SellerDAO;
import com.gameshub.repository.user.BuyerRepository;
import com.gameshub.repository.user.SellerRepository;
import com.gameshub.utils.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminService {
    private final UserMapper userMapper;
    private final BuyerRepository buyerRepository;
    private final SellerRepository sellerRepository;

    public List<BuyerDTO> getAllBuyers() {
        List<BuyerDAO> buyerDAOs = buyerRepository.findAll();
        return userMapper.toBuyerDTOList(buyerDAOs);
    }

    public List<SellerDTO> getAllSellers() {
        List<SellerDAO> sellerDAOs = sellerRepository.findAll();
        return userMapper.toSellerDTOList(sellerDAOs);
    }

    public void deleteBuyer(int buyerId) {
        buyerRepository.deleteById(buyerId);
    }

    public void deleteSeller(int sellerId) {
        sellerRepository.deleteById(sellerId);
    }

}

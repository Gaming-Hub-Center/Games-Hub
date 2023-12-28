package com.gameshub.service.user;

import com.gameshub.controller.DTO.ProductBriefDTO;
import com.gameshub.controller.DTO.user.AdminDTO;
import com.gameshub.controller.DTO.user.BuyerDTO;
import com.gameshub.controller.DTO.user.SellerDTO;
import com.gameshub.model.user.AdminDAO;
import com.gameshub.model.user.BuyerDAO;
import com.gameshub.model.user.SellerDAO;
import com.gameshub.repository.product.DigitalImageRepository;
import com.gameshub.repository.product.DigitalProductRepository;
import com.gameshub.repository.product.PhysicalImageRepository;
import com.gameshub.repository.product.PhysicalProductRepository;
import com.gameshub.repository.user.AdminRepository;
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
    private final AdminRepository adminRepository;
    private final PhysicalProductRepository physicalProductRepository;
    private final DigitalProductRepository digitalProductRepository;
    private final PhysicalImageRepository physicalImageRepository;
    private final DigitalImageRepository digitalImageRepository;

    public List<BuyerDTO> getAllBuyers() {
        List<BuyerDAO> buyerDAOs = buyerRepository.findAll();
        return userMapper.toBuyerDTOList(buyerDAOs);
    }

    public List<SellerDTO> getAllSellers() {
        List<SellerDAO> sellerDAOs = sellerRepository.findAll();
        return userMapper.toSellerDTOList(sellerDAOs);
    }

    public List<AdminDTO> getAllAdmins(int adminId) {
        List<AdminDAO> adminDAOs = adminRepository.findAll();
        adminDAOs.removeIf(admin -> admin.getId() == adminId);
        return userMapper.toAdminDTOList(adminDAOs);
    }

    public void deleteBuyer(int buyerId) {
        buyerRepository.deleteById(buyerId);
    }

    public void deleteSeller(int sellerId) {
        sellerRepository.deleteById(sellerId);
    }

    public void deleteAdmin(int adminId) {
        adminRepository.deleteById(adminId);
    }

    public void deletePhysicalProduct(int productId) {
        physicalProductRepository.deleteById(productId);
    }

    public void deleteDigitalProduct(int productId) {
        digitalProductRepository.deleteById(productId);
    }

    public List<ProductBriefDTO> getSellerPhysicalProducts(int sellerId) {
        List<ProductBriefDTO> products = physicalProductRepository.getAllBySellerID(sellerId);
        for(ProductBriefDTO product: products) {
            product.setImage(physicalImageRepository.findByProduct_id(product.getId()).orElse(null));
            product.setProductType("physical");
        }
        return products;
    }

    public List<ProductBriefDTO> getSellerDigitalProducts(int sellerId) {
        List<ProductBriefDTO> products = digitalProductRepository.getAllBySellerID(sellerId);
        for(ProductBriefDTO product: products) {
            product.setImage(digitalImageRepository.findByProduct_id(product.getId()).orElse(null));
            product.setProductType("digital");
        }
        return products;
    }

}
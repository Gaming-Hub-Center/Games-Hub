package com.gameshub.service.product;

import com.gameshub.dto.product.DigitalProductDTO;
import com.gameshub.dto.product.PhysicalProductDTO;
import com.gameshub.dto.product.ProductBriefDTO;
import com.gameshub.dto.product.ProductPatchDTO;
import com.gameshub.enums.ProductType;
import com.gameshub.exception.ResourceNotFoundException;
import com.gameshub.model.product.DigitalProductDAO;
import com.gameshub.model.product.PhysicalProductDAO;
import com.gameshub.repository.product.DigitalImageRepository;
import com.gameshub.repository.product.DigitalProductRepository;
import com.gameshub.repository.product.PhysicalImageRepository;
import com.gameshub.repository.product.PhysicalProductRepository;
import com.gameshub.utils.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final PhysicalProductRepository physicalProductRepository;
    private final DigitalProductRepository digitalProductRepository;
    private final PhysicalImageRepository physicalImageRepository;
    private final DigitalImageRepository digitalImageRepository;
    private final ProductMapper productMapper;

    public List<PhysicalProductDAO> getAllPhysicalProductsBySellerId(int sellerId) {
        return physicalProductRepository.findBySellerId(sellerId);
    }

    public List<DigitalProductDAO> getAllDigitalProductsBySellerId(int sellerId) {
        return digitalProductRepository.findBySellerId(sellerId);
    }

    public DigitalProductDAO getDigitalProductByProductId(int productId) {
        Optional<DigitalProductDAO> foundProduct = digitalProductRepository.findById(productId);
        return foundProduct.orElse(null);
    }

    public PhysicalProductDAO getPhysicalProductByProductId(int productId) {
        Optional<PhysicalProductDAO> foundProduct = physicalProductRepository.findById(productId);
        return foundProduct.orElse(null);
    }

    public boolean deleteDigitalProductBySellerIdAndProductId(int sellerId, int productId) {
        long numberOfDeletedProducts = digitalProductRepository.deleteByIdAndSellerId(productId, sellerId);
        return numberOfDeletedProducts != 0;
    }

    public boolean deletePhysicalProductBySellerIdAndProductId(int sellerId, int productId) {
        long numberOfDeletedProducts = physicalProductRepository.deleteByIdAndSellerId(productId, sellerId);
        return numberOfDeletedProducts != 0;
    }

    public boolean updateDigitalProductByProductId(int productId, ProductPatchDTO patch) {
        int numberOfUpdatedProducts = digitalProductRepository.updateById(productId, patch);
        return numberOfUpdatedProducts != 0;
    }

    public boolean updatePhysicalProductByProductId(int productId, ProductPatchDTO patch) {
        int numberOfUpdatedProducts = physicalProductRepository.updateById(productId, patch);
        return numberOfUpdatedProducts != 0;
    }

    public PhysicalProductDTO getPhysicalById(int id) {
        PhysicalProductDAO productDAO = getPhysicalProductByProductId(id);
        PhysicalProductDTO productDTO = productMapper.toPhysicalProductDTO(productDAO);
        if(productDAO != null) {
            List<byte[]> list = physicalImageRepository.findAllByProduct_id(id).orElse(null);
            productDTO.setImages(list);
        } else {
            throw new ResourceNotFoundException("Product with ID: " + id + " is not found!");
        }
        return productDTO;
    }

    public DigitalProductDTO getDigitalById(int id) {
        DigitalProductDAO productDAO = getDigitalProductByProductId(id);
        DigitalProductDTO productDTO = productMapper.toDigitalProductDTO(productDAO);
        if(productDAO != null) {
            List<byte[]> list = digitalImageRepository.findAllByProduct_id(id).orElse(null);
            productDTO.setImages(list);
        } else {
            throw new ResourceNotFoundException("Product with ID: " + id + " is not found!");
        }
        return productDTO;
    }

    public List<ProductBriefDTO> searchPhysicalByTitle(String key) {
        List<ProductBriefDTO> productDTOs = physicalProductRepository.findAllByTitleContainingIgnoreCase(key).orElse(null);
<<<<<<< Updated upstream
        if(productDTOs == null || productDTOs.isEmpty()) return productDTOs;
        for(ProductBriefDTO productDTO: productDTOs) {
            byte[] image = physicalImageRepository.findByProduct_id(productDTO.getId()).orElse(null);
            productDTO.setImage(image);
            productDTO.setProductType("physical");
=======

        if (productDTOs == null || productDTOs.isEmpty())
            return productDTOs;

        for (ProductBriefDTO productDTO: productDTOs) {
            String url = physicalImageRepository.findByProduct_id(productDTO.getId()).orElse(null);
            productDTO.setUrl(url);
            productDTO.setProductType(ProductType.PHYSICAL.name());
>>>>>>> Stashed changes
        }
        return productDTOs;
    }

    public List<ProductBriefDTO> searchDigitalByTitle(String key) {
        List<ProductBriefDTO> productDTOs = digitalProductRepository.findAllByTitleContainingIgnoreCase(key).orElse(null);
<<<<<<< Updated upstream
        if(productDTOs == null || productDTOs.isEmpty()) return productDTOs;
        for(ProductBriefDTO productDTO: productDTOs) {
            byte[] image = digitalImageRepository.findByProduct_id(productDTO.getId()).orElse(null);
            productDTO.setImage(image);
            productDTO.setProductType("digital");
=======

        if (productDTOs == null || productDTOs.isEmpty())
            return productDTOs;

        for (ProductBriefDTO productDTO: productDTOs) {
            String url = digitalImageRepository.findByProduct_id(productDTO.getId()).orElse(null);
            productDTO.setUrl(url);
            productDTO.setProductType(ProductType.DIGITAL.name());
>>>>>>> Stashed changes
        }
        return productDTOs;
    }

    public List<ProductBriefDTO> filterPhysical(float lowerBound, float upperBound, String category) {
        category = (category == null) ? null : category.toLowerCase();
        List<ProductBriefDTO> productDTOs = physicalProductRepository.filterPhysical(lowerBound, upperBound, category);
<<<<<<< Updated upstream
        for(ProductBriefDTO productDTO: productDTOs) {
            byte[] image = physicalImageRepository.findByProduct_id(productDTO.getId()).orElse(null);
            productDTO.setImage(image);
            productDTO.setProductType("physical");
=======
        for (ProductBriefDTO productDTO: productDTOs) {
            String url = physicalImageRepository.findByProduct_id(productDTO.getId()).orElse(null);
            productDTO.setUrl(url);
            productDTO.setProductType(ProductType.PHYSICAL.name());
>>>>>>> Stashed changes
        }
        return productDTOs;
    }

    public List<ProductBriefDTO> filterDigital(float lowerBound, float upperBound, String category) {
        category = (category == null) ? null : category.toLowerCase();
        List<ProductBriefDTO> productDTOs = digitalProductRepository.filterPhysical(lowerBound, upperBound, category);
<<<<<<< Updated upstream
        for(ProductBriefDTO productDTO: productDTOs) {
            byte[] image = digitalImageRepository.findByProduct_id(productDTO.getId()).orElse(null);
            productDTO.setImage(image);
            productDTO.setProductType("digital");
=======
        for (ProductBriefDTO productDTO: productDTOs) {
            String url = digitalImageRepository.findByProduct_id(productDTO.getId()).orElse(null);
            productDTO.setUrl(url);
            productDTO.setProductType(ProductType.DIGITAL.name());
>>>>>>> Stashed changes
        }
        return productDTOs;
    }

    public List<ProductBriefDTO> getAllPhysical() {
        List<ProductBriefDTO> productDTOs = physicalProductRepository.findAllProducts().orElse(null);
<<<<<<< Updated upstream
        for(ProductBriefDTO productDTO: productDTOs) {
            byte[] image = physicalImageRepository.findByProduct_id(productDTO.getId()).orElse(null);
            productDTO.setImage(image);
            productDTO.setProductType("physical");
=======

        if (productDTOs == null || productDTOs.isEmpty())
            return productDTOs;

        for (ProductBriefDTO productDTO: productDTOs) {
            String url = physicalImageRepository.findByProduct_id(productDTO.getId()).orElse(null);
            productDTO.setUrl(url);
            productDTO.setProductType(ProductType.PHYSICAL.name());
>>>>>>> Stashed changes
        }
        return productDTOs;
    }

    public List<ProductBriefDTO> getAllDigital() {
        List<ProductBriefDTO> productDTOs = digitalProductRepository.findAllProducts().orElse(null);
<<<<<<< Updated upstream
        for(ProductBriefDTO productDTO: productDTOs) {
            byte[] image = digitalImageRepository.findByProduct_id(productDTO.getId()).orElse(null);
            productDTO.setImage(image);
            productDTO.setProductType("digital");
=======

        if (productDTOs == null || productDTOs.isEmpty())
            return productDTOs;

        for (ProductBriefDTO productDTO: productDTOs) {
            String url = digitalImageRepository.findByProduct_id(productDTO.getId()).orElse(null);
            productDTO.setUrl(url);
            productDTO.setProductType(ProductType.DIGITAL.name());
>>>>>>> Stashed changes
        }
        return productDTOs;
    }

    public List<ProductBriefDTO> sortPhysical(boolean ascending) {
        List<ProductBriefDTO> productDTOs = physicalProductRepository.getOrderedByPrice().orElse(null);
<<<<<<< Updated upstream
        if(productDTOs == null || productDTOs.isEmpty()) return productDTOs;
        for(ProductBriefDTO productDTO: productDTOs) {
            byte[] image = physicalImageRepository.findByProduct_id(productDTO.getId()).orElse(null);
            productDTO.setImage(image);
=======

        if (productDTOs == null || productDTOs.isEmpty())
            return productDTOs;

        for (ProductBriefDTO productDTO: productDTOs) {
            String url = physicalImageRepository.findByProduct_id(productDTO.getId()).orElse(null);
            productDTO.setUrl(url);
>>>>>>> Stashed changes
        }
        return ascending ? productDTOs : productDTOs.reversed();
    }

    public List<ProductBriefDTO> sortDigital(boolean ascending) {
        List<ProductBriefDTO> productDTOs = digitalProductRepository.getOrderedByPrice().orElse(null);
<<<<<<< Updated upstream
        if(productDTOs == null || productDTOs.isEmpty()) return productDTOs;
        for(ProductBriefDTO productDTO: productDTOs) {
            byte[] image = digitalImageRepository.findByProduct_id(productDTO.getId()).orElse(null);
            productDTO.setImage(image);
=======

        if (productDTOs == null || productDTOs.isEmpty())
            return productDTOs;

        for (ProductBriefDTO productDTO: productDTOs) {
            String url = digitalImageRepository.findByProduct_id(productDTO.getId()).orElse(null);
            productDTO.setUrl(url);
>>>>>>> Stashed changes
        }
        return ascending ? productDTOs : productDTOs.reversed();
    }

<<<<<<< Updated upstream
    public void save(PhysicalProductDAO physicalProductDAO, List<PhysicalImageDAO> list) {
        physicalProductRepository.save(physicalProductDAO);
        for (PhysicalImageDAO imageDAO: list) {
            imageDAO.setProduct_id(physicalProductDAO.getId());
            physicalImageRepository.save(imageDAO);
        }
    }

    public void save(DigitalProductDAO digitalProductDAO, List<DigitalImageDAO> list) {
        digitalProductRepository.save(digitalProductDAO);
        for (DigitalImageDAO imageDAO: list) {
            imageDAO.setProduct_id(digitalProductDAO.getId());
            digitalImageRepository.save(imageDAO);
        }
=======
    public void decreasePhysicalProductCount(int productID, int count) {
        PhysicalProductDAO productDAO = getPhysicalProductByProductId(productID);
        productDAO.setCount(productDAO.getCount() - count);
        physicalProductRepository.save(productDAO);
    }

    public void decreaseDigitalProductCount(int productID, int count) {
        DigitalProductDAO productDAO = getDigitalProductByProductId(productID);
        productDAO.setCount(productDAO.getCount() - count);
        digitalProductRepository.save(productDAO);
>>>>>>> Stashed changes
    }

}

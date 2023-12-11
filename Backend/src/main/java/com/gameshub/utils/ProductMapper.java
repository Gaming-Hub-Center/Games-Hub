package com.gameshub.utils;

import com.gameshub.controller.DTO.product.*;
import com.gameshub.model.product.*;
import com.gameshub.model.user.SellerDAO;
import com.gameshub.repository.user.SellerRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Data
public class ProductMapper {

    @Autowired
    private SellerRepository repository;

    public DigitalProductDTO toProductDTO(DigitalProductDAO digitalProductDAO) {
        if ( digitalProductDAO == null ) {
            return null;
        }

        DigitalProductDTO digitalProductDTO = new DigitalProductDTO();

        digitalProductDTO.setId( digitalProductDAO.getId() );
        digitalProductDTO.setTitle( digitalProductDAO.getTitle() );
        digitalProductDTO.setPrice( digitalProductDAO.getPrice() );
        digitalProductDTO.setDescription( digitalProductDAO.getDescription() );
        digitalProductDTO.setPostDate( digitalProductDAO.getPostDate() );
        digitalProductDTO.setCount( digitalProductDAO.getCount() );
        digitalProductDTO.setCategory(digitalProductDAO.getCategory() );

        if(digitalProductDAO.getSeller() != null)
            digitalProductDTO.setSellerEmail( digitalProductDAO.getSeller().getEmail() );

        digitalProductDTO.setCode( digitalProductDAO.getCode() );

        return digitalProductDTO;
    }

    public PhysicalProductDTO toProductDTO(PhysicalProductDAO physicalProductDAO) {
        if ( physicalProductDAO == null ) {
            return null;
        }

        PhysicalProductDTO physicalProductDTO = new PhysicalProductDTO();

        physicalProductDTO.setId( physicalProductDAO.getId() );
        physicalProductDTO.setTitle( physicalProductDAO.getTitle() );
        physicalProductDTO.setPrice( physicalProductDAO.getPrice() );
        physicalProductDTO.setDescription( physicalProductDAO.getDescription() );
        physicalProductDTO.setPostDate( physicalProductDAO.getPostDate() );
        physicalProductDTO.setCount( physicalProductDAO.getCount() );
        physicalProductDTO.setCategory(physicalProductDAO.getCategory() );

        if(physicalProductDAO.getSeller() != null)
            physicalProductDTO.setSellerEmail( physicalProductDAO.getSeller().getEmail() );

        return physicalProductDTO;
    }

    public DigitalProductDAO toProductDAO(DigitalProductDTO digitalProductDTO) {
        if ( digitalProductDTO == null ) {
            return null;
        }

        DigitalProductDAO digitalProductDAO = new DigitalProductDAO();

        digitalProductDAO.setId( digitalProductDTO.getId() );
        digitalProductDAO.setTitle( digitalProductDTO.getTitle() );
        digitalProductDAO.setPrice( digitalProductDTO.getPrice() );
        digitalProductDAO.setDescription( digitalProductDTO.getDescription() );
        digitalProductDAO.setPostDate( digitalProductDTO.getPostDate() );
        digitalProductDAO.setCount( digitalProductDTO.getCount() );
        digitalProductDAO.setCategory(digitalProductDTO.getCategory() );

        Optional<SellerDAO> seller = repository.findByEmail(digitalProductDTO.getSellerEmail());

        seller.ifPresent(digitalProductDAO::setSeller);

        digitalProductDAO.setCode( digitalProductDTO.getCode() );

        return digitalProductDAO;
    }

    public PhysicalProductDAO toProductDAO(PhysicalProductDTO physicalProductDTO) {
        if ( physicalProductDTO == null ) {
            return null;
        }

        PhysicalProductDAO physicalProductDAO = new PhysicalProductDAO();

        physicalProductDAO.setId( physicalProductDTO.getId() );
        physicalProductDAO.setTitle( physicalProductDTO.getTitle() );
        physicalProductDAO.setPrice( physicalProductDTO.getPrice() );
        physicalProductDAO.setDescription( physicalProductDTO.getDescription() );
        physicalProductDAO.setPostDate( physicalProductDTO.getPostDate() );
        physicalProductDAO.setCount( physicalProductDTO.getCount() );
        physicalProductDAO.setCategory(physicalProductDTO.getCategory() );

        Optional<SellerDAO> seller = repository.findByEmail(physicalProductDTO.getSellerEmail());

        seller.ifPresent(physicalProductDAO::setSeller);

        return physicalProductDAO;
    }
}

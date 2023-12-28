package com.gameshub.utils;

import com.gameshub.controller.DTO.request.*;
import com.gameshub.model.request.*;
import com.gameshub.model.user.*;
import com.gameshub.service.user.*;
import lombok.*;
import org.springframework.stereotype.*;

@RequiredArgsConstructor
@Component
public class ProductRequestMapper {

    public final UserService userService;

    public DigitalProductRequestDAO toDAO(DigitalProductRequestDTO digitalProductRequestDTO) {
        if ( digitalProductRequestDTO == null ) {
            return null;
        }

        DigitalProductRequestDAO digitalProductRequestDAO = new DigitalProductRequestDAO();

        digitalProductRequestDAO.setDateReceived( digitalProductRequestDTO.getDateReceived() );
        digitalProductRequestDAO.setStatus( digitalProductRequestDTO.getStatus() );
        digitalProductRequestDAO.setRequestType( digitalProductRequestDTO.getRequestType() );
        digitalProductRequestDAO.setTitle( digitalProductRequestDTO.getTitle() );
        digitalProductRequestDAO.setPrice( digitalProductRequestDTO.getPrice() );
        digitalProductRequestDAO.setDescription( digitalProductRequestDTO.getDescription() );
        digitalProductRequestDAO.setPostDate( digitalProductRequestDTO.getPostDate() );
        digitalProductRequestDAO.setCount( digitalProductRequestDTO.getCount() );

        SellerDAO sellerDAO = userService.getSellerById(digitalProductRequestDTO.getSellerId());
        digitalProductRequestDAO.setSeller(sellerDAO);

        digitalProductRequestDAO.setCategory( digitalProductRequestDTO.getCategory() );
        digitalProductRequestDAO.setCode( digitalProductRequestDTO.getCode() );

        return digitalProductRequestDAO;
    }

    public DigitalProductRequestDTO toDTO(DigitalProductRequestDAO digitalProductRequestDAO) {
        if ( digitalProductRequestDAO == null ) {
            return null;
        }

        DigitalProductRequestDTO digitalProductRequestDTO = new DigitalProductRequestDTO();

        digitalProductRequestDTO.setId( digitalProductRequestDAO.getId() );
        digitalProductRequestDTO.setDateReceived( digitalProductRequestDAO.getDateReceived() );
        digitalProductRequestDTO.setStatus( digitalProductRequestDAO.getStatus() );
        digitalProductRequestDTO.setRequestType( digitalProductRequestDAO.getRequestType() );
        digitalProductRequestDTO.setTitle( digitalProductRequestDAO.getTitle() );
        digitalProductRequestDTO.setPrice( digitalProductRequestDAO.getPrice() );
        digitalProductRequestDTO.setDescription( digitalProductRequestDAO.getDescription() );
        digitalProductRequestDTO.setPostDate( digitalProductRequestDAO.getPostDate() );
        digitalProductRequestDTO.setCount( digitalProductRequestDAO.getCount() );

        if(digitalProductRequestDAO.getSeller() != null)
            digitalProductRequestDTO.setSellerId( digitalProductRequestDAO.getSeller().getId() );

        digitalProductRequestDTO.setCategory( digitalProductRequestDAO.getCategory() );
        digitalProductRequestDTO.setCode( digitalProductRequestDAO.getCode() );

        return digitalProductRequestDTO;
    }

    public PhysicalProductRequestDAO toDAO(PhysicalProductRequestDTO physicalProductRequestDTO) {
        if ( physicalProductRequestDTO == null ) {
            return null;
        }

        PhysicalProductRequestDAO physicalProductRequestDAO = new PhysicalProductRequestDAO();

        physicalProductRequestDAO.setDateReceived( physicalProductRequestDTO.getDateReceived() );
        physicalProductRequestDAO.setStatus( physicalProductRequestDTO.getStatus() );
        physicalProductRequestDAO.setRequestType( physicalProductRequestDTO.getRequestType() );
        physicalProductRequestDAO.setTitle( physicalProductRequestDTO.getTitle() );
        physicalProductRequestDAO.setPrice( physicalProductRequestDTO.getPrice() );
        physicalProductRequestDAO.setDescription( physicalProductRequestDTO.getDescription() );
        physicalProductRequestDAO.setPostDate( physicalProductRequestDTO.getPostDate() );
        physicalProductRequestDAO.setCount( physicalProductRequestDTO.getCount() );

        SellerDAO sellerDAO = userService.getSellerById(physicalProductRequestDTO.getSellerId());
        physicalProductRequestDAO.setSeller(sellerDAO);

        physicalProductRequestDAO.setCategory( physicalProductRequestDTO.getCategory() );

        return physicalProductRequestDAO;
    }

    public PhysicalProductRequestDTO toDTO(PhysicalProductRequestDAO physicalProductRequestDAO) {
        if ( physicalProductRequestDAO == null ) {
            return null;
        }

        PhysicalProductRequestDTO physicalProductRequestDTO = new PhysicalProductRequestDTO();

        physicalProductRequestDTO.setId( physicalProductRequestDAO.getId() );
        physicalProductRequestDTO.setDateReceived( physicalProductRequestDAO.getDateReceived() );
        physicalProductRequestDTO.setStatus( physicalProductRequestDAO.getStatus() );
        physicalProductRequestDTO.setRequestType( physicalProductRequestDAO.getRequestType() );
        physicalProductRequestDTO.setTitle( physicalProductRequestDAO.getTitle() );
        physicalProductRequestDTO.setPrice( physicalProductRequestDAO.getPrice() );
        physicalProductRequestDTO.setDescription( physicalProductRequestDAO.getDescription() );
        physicalProductRequestDTO.setPostDate( physicalProductRequestDAO.getPostDate() );
        physicalProductRequestDTO.setCount( physicalProductRequestDAO.getCount() );

        if(physicalProductRequestDAO.getSeller() != null)
            physicalProductRequestDTO.setSellerId( physicalProductRequestDAO.getSeller().getId() );

        physicalProductRequestDTO.setCategory( physicalProductRequestDAO.getCategory() );

        return physicalProductRequestDTO;
    }

}

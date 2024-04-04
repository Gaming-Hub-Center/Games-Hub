package com.gameshub.utils;

import com.gameshub.dto.product.DigitalProductRequestDTO;
import com.gameshub.dto.product.PhysicalProductRequestDTO;
import com.gameshub.model.request.DigitalProductRequestDAO;
import com.gameshub.model.request.PhysicalProductRequestDAO;
import com.gameshub.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProductRequestMapper {

    public final UserService userService;

    public DigitalProductRequestDAO toDAO(DigitalProductRequestDTO digitalProductRequestDTO) {
        if ( digitalProductRequestDTO == null )
            return null;

        DigitalProductRequestDAO digitalProductRequestDAO = new DigitalProductRequestDAO();

        digitalProductRequestDAO.setDateReceived( digitalProductRequestDTO.getDateReceived() );
        digitalProductRequestDAO.setStatus( digitalProductRequestDTO.getStatus() );
        digitalProductRequestDAO.setRequestType( digitalProductRequestDTO.getRequestType() );
        digitalProductRequestDAO.setTitle( digitalProductRequestDTO.getTitle() );
        digitalProductRequestDAO.setPrice( digitalProductRequestDTO.getPrice() );
        digitalProductRequestDAO.setDescription( digitalProductRequestDTO.getDescription() );
        digitalProductRequestDAO.setPostDate( digitalProductRequestDTO.getPostDate() );
        digitalProductRequestDAO.setCount( digitalProductRequestDTO.getCount() );
        digitalProductRequestDAO.setSellerId( digitalProductRequestDTO.getSellerId() );
        digitalProductRequestDAO.setCategory( digitalProductRequestDTO.getCategory() );

        return digitalProductRequestDAO;
    }

    public DigitalProductRequestDTO toDTO(DigitalProductRequestDAO digitalProductRequestDAO) {
        if ( digitalProductRequestDAO == null )
            return null;

        DigitalProductRequestDTO digitalProductRequestDTO = new DigitalProductRequestDTO();

        digitalProductRequestDTO.setDateReceived( digitalProductRequestDAO.getDateReceived() );
        digitalProductRequestDTO.setStatus( digitalProductRequestDAO.getStatus() );
        digitalProductRequestDTO.setRequestType( digitalProductRequestDAO.getRequestType() );
        digitalProductRequestDTO.setTitle( digitalProductRequestDAO.getTitle() );
        digitalProductRequestDTO.setPrice( digitalProductRequestDAO.getPrice() );
        digitalProductRequestDTO.setDescription( digitalProductRequestDAO.getDescription() );
        digitalProductRequestDTO.setPostDate( digitalProductRequestDAO.getPostDate() );
        digitalProductRequestDTO.setCount( digitalProductRequestDAO.getCount() );
        digitalProductRequestDTO.setSellerId( digitalProductRequestDAO.getSellerId() );
        digitalProductRequestDTO.setCategory( digitalProductRequestDAO.getCategory() );

        return digitalProductRequestDTO;
    }

    public PhysicalProductRequestDAO toDAO(PhysicalProductRequestDTO physicalProductRequestDTO) {
        if ( physicalProductRequestDTO == null )
            return null;

        PhysicalProductRequestDAO physicalProductRequestDAO = new PhysicalProductRequestDAO();

        physicalProductRequestDAO.setDateReceived( physicalProductRequestDTO.getDateReceived() );
        physicalProductRequestDAO.setStatus( physicalProductRequestDTO.getStatus() );
        physicalProductRequestDAO.setRequestType( physicalProductRequestDTO.getRequestType() );
        physicalProductRequestDAO.setTitle( physicalProductRequestDTO.getTitle() );
        physicalProductRequestDAO.setPrice( physicalProductRequestDTO.getPrice() );
        physicalProductRequestDAO.setDescription( physicalProductRequestDTO.getDescription() );
        physicalProductRequestDAO.setPostDate( physicalProductRequestDTO.getPostDate() );
        physicalProductRequestDAO.setCount( physicalProductRequestDTO.getCount() );
        physicalProductRequestDAO.setSellerId( physicalProductRequestDTO.getSellerId() );
        physicalProductRequestDAO.setCategory( physicalProductRequestDTO.getCategory() );

        return physicalProductRequestDAO;
    }

    public PhysicalProductRequestDTO toDTO(PhysicalProductRequestDAO physicalProductRequestDAO) {
        if ( physicalProductRequestDAO == null )
            return null;

        PhysicalProductRequestDTO physicalProductRequestDTO = new PhysicalProductRequestDTO();

        physicalProductRequestDTO.setDateReceived( physicalProductRequestDAO.getDateReceived() );
        physicalProductRequestDTO.setStatus( physicalProductRequestDAO.getStatus() );
        physicalProductRequestDTO.setRequestType( physicalProductRequestDAO.getRequestType() );
        physicalProductRequestDTO.setTitle( physicalProductRequestDAO.getTitle() );
        physicalProductRequestDTO.setPrice( physicalProductRequestDAO.getPrice() );
        physicalProductRequestDTO.setDescription( physicalProductRequestDAO.getDescription() );
        physicalProductRequestDTO.setPostDate( physicalProductRequestDAO.getPostDate() );
        physicalProductRequestDTO.setCount( physicalProductRequestDAO.getCount() );
        physicalProductRequestDTO.setSellerId( physicalProductRequestDAO.getSellerId() );
        physicalProductRequestDTO.setCategory( physicalProductRequestDAO.getCategory() );

        return physicalProductRequestDTO;
    }

}

package com.gameshub.utils;

import com.gameshub.controller.DTO.user.*;
import com.gameshub.model.user.*;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toUserDTO(UserDAO userDAO);

    BuyerDTO toUserDTO(BuyerDAO buyerDAO);

    SellerDTO toUserDTO(SellerDAO sellerDAO);

    @Mapping(target = "password", ignore = true)
    BuyerDAO toUserDAO(BuyerRegistrationDTO buyerRegistrationDTO);

    @Mapping(target = "password", ignore = true)
    SellerDAO toUserDAO(SellerRegistrationDTO sellerRegistrationDTO);

    List<BuyerDTO> toBuyerDTOList(List<BuyerDAO> buyerDAOs);

    List<SellerDTO> toSellerDTOList(List<SellerDAO> sellerDAOs);

}
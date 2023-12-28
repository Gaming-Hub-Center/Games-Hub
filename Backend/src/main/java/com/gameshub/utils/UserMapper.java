package com.gameshub.utils;

import com.gameshub.controller.DTO.user.*;
import com.gameshub.model.user.*;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "role", constant = "USER")
    UserDTO toUserDTO(UserDAO userDAO);

    @Mapping(target = "role", constant = "BUYER")
    BuyerDTO toUserDTO(BuyerDAO buyerDAO);

    @Mapping(target = "role", constant = "SELLER")
    SellerDTO toUserDTO(SellerDAO sellerDAO);

    @Mapping(target = "password", ignore = true)
    BuyerDAO toUserDAO(BuyerRegistrationDTO buyerRegistrationDTO);

    @Mapping(target = "password", ignore = true)
    SellerDAO toUserDAO(SellerRegistrationDTO sellerRegistrationDTO);

    List<BuyerDTO> toBuyerDTOList(List<BuyerDAO> buyerDAOs);

    List<SellerDTO> toSellerDTOList(List<SellerDAO> sellerDAOs);

}
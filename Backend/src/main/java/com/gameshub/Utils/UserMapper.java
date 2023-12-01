package com.gameshub.Utils;

import com.gameshub.Model.User.DAO.*;
import com.gameshub.Model.User.DTO.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toUserDTO(UserDAO userDAO);

    BuyerDTO toUserDTO(BuyerDAO buyerDAO);

    SellerDTO toUserDTO(SellerDAO sellerDAO);

    @Mapping(target = "password", ignore = true)
    BuyerDAO toUserDAO(BuyerRegistrationDTO buyerRegistrationDTO);

    @Mapping(target = "password", ignore = true)
    SellerRegistrationDTO toUserDAO(SellerRegistrationDTO sellerRegistrationDTO);

}
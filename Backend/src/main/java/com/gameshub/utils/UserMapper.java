package com.gameshub.utils;

import com.gameshub.dto.user.*;
import com.gameshub.model.user.AdminDAO;
import com.gameshub.model.user.BuyerDAO;
import com.gameshub.model.user.SellerDAO;
import com.gameshub.model.user.UserDAO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toUserDTO(UserDAO userDAO);

    BuyerDTO toUserDTO(BuyerDAO buyerDAO);

    SellerDTO toUserDTO(SellerDAO sellerDAO);

<<<<<<< Updated upstream
=======
    AdminDTO toUserDTO(AdminDAO adminDAO);

>>>>>>> Stashed changes
    @Mapping(target = "password", ignore = true)
    BuyerDAO toUserDAO(BuyerRegistrationDTO buyerRegistrationDTO);

    @Mapping(target = "password", ignore = true)
    SellerDAO toUserDAO(SellerRegistrationDTO sellerRegistrationDTO);

<<<<<<< Updated upstream
=======
    List<BuyerDTO> toBuyerDTOList(List<BuyerDAO> buyerDAOs);

    List<SellerDTO> toSellerDTOList(List<SellerDAO> sellerDAOs);

    List<AdminDTO> toAdminDTOList(List<AdminDAO> adminDAOs);

>>>>>>> Stashed changes
}
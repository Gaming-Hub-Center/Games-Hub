package com.gameshub.products;

import com.gameshub.repository.user.SellerRepository;
import com.gameshub.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductMapperTest {

    private ProductMapper productMapper;

    @Autowired
    private SellerRepository sellerRepository;

}

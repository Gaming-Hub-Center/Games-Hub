package com.gameshub.service;

import com.gameshub.exception.*;
import com.gameshub.model.cart.*;
import com.gameshub.model.order.*;
import com.gameshub.model.product.*;
import com.gameshub.model.user.*;
import com.gameshub.repository.order.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.time.*;
import java.util.*;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final CartService cartService;
//    private final ProductService productService;
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final PhysicalOrderItemRepository physicalOrderItemRepository;
    private final DigitalOrderItemRepository digitalOrderItemRepository;

    public void orderPhysical(int buyerID, boolean isWallet) {
        // TODO: decrease buyer wallet and increase seller's wallets

        List<PhysicalCartDAO> physicalCartDAOs = cartService.getPhysicalCartItems(buyerID);
        List<PhysicalOrderItemDAO> physicalOrderItemDAOs = new ArrayList<>();
        float orderPrice = 0;

        for (PhysicalCartDAO cartProductItem : physicalCartDAOs) {

            PhysicalProductDAO product = cartProductItem.getProduct();

            if (product == null)
                throw new ResourceNotFoundException("Sorry, the product you are looking for is no longer available.");

            if (product.getCount() < cartProductItem.getCount())
                throw new OutOfStockException("The product " + product.getTitle() + " has only " + product.getCount() + " pieces left!");

//            productService.updatePhysical(product.getId(), product.getCount() - physicalCartDAO.getCount());

            PhysicalOrderItemDAO physicalOrderItemDAO = new PhysicalOrderItemDAO(
                new PhysicalOrderItemDAO.PhysicalOrderItemId(0, product),
                cartProductItem.getCount(),
                product.getPrice(),
                (float) cartProductItem.getCount() * product.getPrice()
            );

            physicalOrderItemDAOs.add(physicalOrderItemDAO);
            orderPrice += physicalOrderItemDAO.getTotalPrice();
        }

        BuyerDAO buyer = userService.getBuyerById(buyerID);

        if (isWallet && buyer.getBalance() < orderPrice)
            throw new InsufficientBalanceException("Sorry, your wallet balance is insufficient for this transaction");

        OrderDAO orderDAO = new OrderDAO(
                buyer,
                LocalDate.now(),
                orderPrice,
                "Processing"
        );

        orderRepository.save(orderDAO);

        for (PhysicalOrderItemDAO physicalOrderItemDAO : physicalOrderItemDAOs)
            physicalOrderItemDAO.getId().setOrderID(orderDAO.getOrderID());

        physicalOrderItemRepository.saveAll(physicalOrderItemDAOs);

        // cartService.clearPhysicalCart(buyerID);
    }

    public void orderDigital(int buyerID, boolean isWallet) {
        // TODO: decrease buyer wallet and increase seller's wallets

        List<DigitalCartDAO> digitalCartDAOs = cartService.getDigitalCartItems(buyerID);
        List<DigitalOrderItemDAO> digitalOrderItemDAOs = new ArrayList<>();
        float orderPrice = 0;

        for (DigitalCartDAO cartProductItem : digitalCartDAOs) {

            DigitalProductDAO product = cartProductItem.getProduct();

            if (product == null)
                throw new ResourceNotFoundException("Sorry, the product you are looking for is no longer available.");

            if (product.getCount() < cartProductItem.getCount())
                throw new OutOfStockException("The product " + product.getTitle() + " has only " + product.getCount() + " pieces left!");

//            productService.updateDigital(product.getId(), product.getCount() - digitalCartDAO.getCount());

            DigitalOrderItemDAO digitalOrderItemDAO = new DigitalOrderItemDAO(
                    new DigitalOrderItemDAO.DigitalOrderItemId(0, product),
                    cartProductItem.getCount(),
                    product.getPrice(),
                    (float) cartProductItem.getCount() * product.getPrice()
            );

            digitalOrderItemDAOs.add(digitalOrderItemDAO);
            orderPrice += digitalOrderItemDAO.getTotalPrice();
        }

        BuyerDAO buyer = userService.getBuyerById(buyerID);

        if (isWallet && buyer.getBalance() < orderPrice)
            throw new InsufficientBalanceException("Sorry, your wallet balance is insufficient for this transaction");

        OrderDAO orderDAO = new OrderDAO(
                buyer,
                LocalDate.now(),
                orderPrice,
                "Processing"
        );

        orderRepository.save(orderDAO);

        for (DigitalOrderItemDAO physicalOrderItemDAO : digitalOrderItemDAOs)
            physicalOrderItemDAO.getId().setOrderID(orderDAO.getOrderID());

        digitalOrderItemRepository.saveAll(digitalOrderItemDAOs);

        // cartService.clearDigitalCart(buyerID);
    }

}

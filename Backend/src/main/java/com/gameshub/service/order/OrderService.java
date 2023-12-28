package com.gameshub.service.order;

import com.gameshub.exception.*;
import com.gameshub.model.cart.*;
import com.gameshub.model.order.*;
import com.gameshub.model.product.*;
import com.gameshub.model.user.*;
import com.gameshub.repository.order.*;
import com.gameshub.service.cart.*;
import com.gameshub.service.product.*;
import com.gameshub.service.user.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.time.*;
import java.util.*;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final CartService cartService;
    private final ProductService productService;
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final PhysicalOrderItemRepository physicalOrderItemRepository;
    private final DigitalOrderItemRepository digitalOrderItemRepository;

    public List<OrderDAO> getOrders(int buyerID) {
        return orderRepository.findByBuyerId(buyerID).reversed();
    }

    public void orderPhysical(int buyerID, boolean isWallet) {
        List<PhysicalCartDAO> physicalCartDAOs = cartService.getPhysicalCartItems(buyerID);
        List<PhysicalOrderItemDAO> physicalOrderItemDAOs = preparePhysicalOrderItems(physicalCartDAOs);
        float orderPrice = calculateTotalPhysicalOrderPrice(physicalOrderItemDAOs);

        validateBuyerBalance(buyerID, isWallet, orderPrice);
        OrderDAO orderDAO = createOrder(buyerID, orderPrice, isWallet);
        updatePhysicalOrderItemsWithOrderId(physicalOrderItemDAOs, orderDAO);
        cartService.deletePhysicalCartItems(userService.getBuyerById(buyerID));
        updateBuyerBalance(buyerID, isWallet, orderPrice);
        updatePhysicalProductsCounts(physicalOrderItemDAOs);
        updatePhysicalSellersBalances(physicalOrderItemDAOs);
    }

    private List<PhysicalOrderItemDAO> preparePhysicalOrderItems(List<PhysicalCartDAO> physicalCartDAOs) {
        if (physicalCartDAOs.isEmpty())
            throw new BadRequestException("Sorry, your cart is empty!");
        List<PhysicalOrderItemDAO> physicalOrderItemDAOs = new ArrayList<>();
        for (PhysicalCartDAO cartProductItem : physicalCartDAOs) {
            PhysicalProductDAO product = cartProductItem.getProduct();
            validatePhysicalProductAvailability(product, cartProductItem);

            PhysicalOrderItemDAO physicalOrderItemDAO = new PhysicalOrderItemDAO(
                    new PhysicalOrderItemDAO.PhysicalOrderItemId(0, product),
                    cartProductItem.getCount(),
                    product.getPrice(),
                    (float) cartProductItem.getCount() * product.getPrice()
            );
            physicalOrderItemDAOs.add(physicalOrderItemDAO);
        }
        return physicalOrderItemDAOs;
    }

    private void validatePhysicalProductAvailability(PhysicalProductDAO product, PhysicalCartDAO cartProductItem) {
        if (product == null)
            throw new ResourceNotFoundException("Sorry, the product you are looking for is no longer available.");
        if (product.getCount() < cartProductItem.getCount())
            throw new OutOfStockException("The product \"" + product.getTitle() + "\" has only " + product.getCount() + " pieces left!");
    }

    private float calculateTotalPhysicalOrderPrice(List<PhysicalOrderItemDAO> physicalOrderItemDAOs) {
        float orderPrice = 0;
        for (PhysicalOrderItemDAO item : physicalOrderItemDAOs)
            orderPrice += item.getTotalPrice();
        return orderPrice;
    }

    private void updatePhysicalOrderItemsWithOrderId(List<PhysicalOrderItemDAO> physicalOrderItemDAOs, OrderDAO orderDAO) {
        for (PhysicalOrderItemDAO item : physicalOrderItemDAOs)
            item.getId().setOrderId(orderDAO.getId());
        physicalOrderItemRepository.saveAll(physicalOrderItemDAOs);
    }

    void updatePhysicalProductsCounts(List<PhysicalOrderItemDAO> physicalOrderItemDAOs) {
        for (PhysicalOrderItemDAO physicalOrderItemDAO : physicalOrderItemDAOs)
            productService.decreasePhysicalProductCount(physicalOrderItemDAO.getId().getPhysicalProductDAO().getId(), physicalOrderItemDAO.getCount());
    }

    void updatePhysicalSellersBalances(List<PhysicalOrderItemDAO> physicalOrderItemDAOs) {
        for (PhysicalOrderItemDAO physicalOrderItemDAO : physicalOrderItemDAOs) {
            PhysicalProductDAO physicalProductDAO = physicalOrderItemDAO.getId().getPhysicalProductDAO();
            int sellerId = physicalProductDAO.getSellerID();
            userService.increaseSellerBalance(sellerId, physicalOrderItemDAO.getTotalPrice());
        }
    }

    public void orderDigital(int buyerID, boolean isWallet) {
        List<DigitalCartDAO> digitalCartDAOs = cartService.getDigitalCartItems(buyerID);
        List<DigitalOrderItemDAO> digitalOrderItemDAOs = prepareOrderItems(digitalCartDAOs);
        float orderPrice = calculateTotalDigitalOrderPrice(digitalOrderItemDAOs);

        validateBuyerBalance(buyerID, isWallet, orderPrice);
        OrderDAO orderDAO = createOrder(buyerID, orderPrice, isWallet);
        updateDigitalOrderItemsWithOrderId(digitalOrderItemDAOs, orderDAO);
        cartService.deleteDigitalCartItems(userService.getBuyerById(buyerID));
        updateBuyerBalance(buyerID, isWallet, orderPrice);
        updateDigitalProductsCounts(digitalOrderItemDAOs);
        updateDigitalSellersBalances(digitalOrderItemDAOs);
    }

    private List<DigitalOrderItemDAO> prepareOrderItems(List<DigitalCartDAO> digitalCartDAOs) {
        List<DigitalOrderItemDAO> digitalOrderItemDAOs = new ArrayList<>();
        for (DigitalCartDAO cartProductItem : digitalCartDAOs) {
            DigitalProductDAO product = cartProductItem.getProduct();
            validateDigitalProductAvailability(product, cartProductItem);

            DigitalOrderItemDAO digitalOrderItemDAO = new DigitalOrderItemDAO(
                    new DigitalOrderItemDAO.DigitalOrderItemId(0, product),
                    cartProductItem.getCount(),
                    product.getPrice(),
                    (float) cartProductItem.getCount() * product.getPrice()
            );
            digitalOrderItemDAOs.add(digitalOrderItemDAO);
        }
        return digitalOrderItemDAOs;
    }

    private void validateDigitalProductAvailability(DigitalProductDAO product, DigitalCartDAO cartProductItem) {
        if (product == null)
            throw new ResourceNotFoundException("Sorry, the product you are looking for is no longer available.");
        if (product.getCount() < cartProductItem.getCount())
            throw new OutOfStockException("The product \"" + product.getTitle() + "\" has only " + product.getCount() + " pieces left!");
    }

    private float calculateTotalDigitalOrderPrice(List<DigitalOrderItemDAO> digitalOrderItemDAOs) {
        float orderPrice = 0;
        for (DigitalOrderItemDAO item : digitalOrderItemDAOs)
            orderPrice += item.getTotalPrice();
        return orderPrice;
    }

    private void updateDigitalOrderItemsWithOrderId(List<DigitalOrderItemDAO> digitalOrderItemDAOs, OrderDAO orderDAO) {
        for (DigitalOrderItemDAO item : digitalOrderItemDAOs)
            item.getId().setOrderId(orderDAO.getId());
        digitalOrderItemRepository.saveAll(digitalOrderItemDAOs);
    }

    void updateDigitalProductsCounts(List<DigitalOrderItemDAO> digitalOrderItemDAOs) {
        for (DigitalOrderItemDAO digitalOrderItemDAO : digitalOrderItemDAOs)
            productService.decreaseDigitalProductCount(digitalOrderItemDAO.getId().getDigitalProductDAO().getId(), digitalOrderItemDAO.getCount());
    }

    void updateDigitalSellersBalances(List<DigitalOrderItemDAO> digitalOrderItemDAOs) {
        for (DigitalOrderItemDAO digitalOrderItemDAO : digitalOrderItemDAOs) {
            DigitalProductDAO digitalProductDAO = digitalOrderItemDAO.getId().getDigitalProductDAO();
            int sellerId = digitalProductDAO.getSellerID();
            userService.increaseSellerBalance(sellerId, digitalOrderItemDAO.getTotalPrice());
        }
    }

    private OrderDAO createOrder(int buyerID, float orderPrice, boolean isWallet) {
        OrderDAO orderDAO = new OrderDAO(buyerID, LocalDate.now(), orderPrice, isWallet ? "Wallet" : "COD", "Processing");
        orderRepository.save(orderDAO);
        return orderDAO;
    }

    void updateBuyerBalance(int buyerID, boolean isWallet, float orderPrice) {
        if (!isWallet)
            return;

        userService.decreaseBuyerBalance(buyerID, orderPrice);
    }

    private void validateBuyerBalance(int buyerID, boolean isWallet, float orderPrice) {
        if (isWallet) {
            BuyerDAO buyer = userService.getBuyerById(buyerID);
            if (buyer.getBalance() < orderPrice)
                throw new InsufficientBalanceException("Sorry, your wallet balance is insufficient for this transaction");
        }
    }

}

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
    private final DigitalCodeRepository digitalCodeRepository;

    public List<OrderDAO> getOrders(int buyerID) {
        List<OrderDAO> orders =  orderRepository.findByBuyerId(buyerID).reversed();

        for (OrderDAO order : orders)
            for (DigitalOrderItemDAO item : order.getDigitalOrderItemDAOs()) {
                List<DigitalCode> digitalCodes = digitalCodeRepository.findById_OrderIdAndId_ProductId(order.getId(), item.getId().getDigitalProductDAO().getId());
                List<String> codeStrings = digitalCodes.stream().map(digitalCode -> digitalCode.getId().getCode()).toList();
                item.setCodes(codeStrings);
            }
        return orders;
    }

    public void orderPhysical(int buyerID, boolean isWallet) {
        List<PhysicalCartDAO> physicalCartDAOs = cartService.getPhysicalCartItems(buyerID);
        List<PhysicalOrderItemDAO> physicalOrderItemDAOs = preparePhysicalOrderItems(physicalCartDAOs);
        float orderPrice = calculateTotalPhysicalOrderPrice(physicalOrderItemDAOs);

        validateBuyerBalance(buyerID, isWallet, orderPrice);
        OrderDAO orderDAO = createOrder(buyerID, orderPrice, isWallet);
        updatePhysicalOrderItemsWithOrderId(physicalOrderItemDAOs, orderDAO.getId());
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

    private void updatePhysicalOrderItemsWithOrderId(List<PhysicalOrderItemDAO> physicalOrderItemDAOs, int orderId) {
        for (PhysicalOrderItemDAO item : physicalOrderItemDAOs)
            item.getId().setOrderId(orderId);
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
        List<DigitalOrderItemDAO> digitalOrderItemDAOs = prepareDigitalOrderItems(digitalCartDAOs);
        float orderPrice = calculateTotalDigitalOrderPrice(digitalOrderItemDAOs);

        validateBuyerBalance(buyerID, isWallet, orderPrice);
        OrderDAO orderDAO = createOrder(buyerID, orderPrice, isWallet);
        updateDigitalOrderItemsWithOrderId(digitalOrderItemDAOs, orderDAO.getId());
        cartService.deleteDigitalCartItems(userService.getBuyerById(buyerID));
        updateBuyerBalance(buyerID, isWallet, orderPrice);
        updateDigitalProductsCounts(digitalOrderItemDAOs);
        updateDigitalSellersBalances(digitalOrderItemDAOs);
    }

    private List<DigitalOrderItemDAO> prepareDigitalOrderItems(List<DigitalCartDAO> digitalCartDAOs) {
        if (digitalCartDAOs.isEmpty())
            throw new BadRequestException("Sorry, your cart is empty!");
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

    private void updateDigitalOrderItemsWithOrderId(List<DigitalOrderItemDAO> digitalOrderItemDAOs, int orderId) {
        for (DigitalOrderItemDAO item : digitalOrderItemDAOs)
            item.getId().setOrderId(orderId);
        digitalOrderItemRepository.saveAll(digitalOrderItemDAOs);

        for (DigitalOrderItemDAO item : digitalOrderItemDAOs)
            addDigitalCodes(orderId, item.getId().getDigitalProductDAO().getId(), item.getCount());
    }

    private void addDigitalCodes(int orderId, int productId, int count) {
        for (int i = 0; i < count; i++) {
            String code = generateDigitalCode();
            digitalCodeRepository.save(new DigitalCode(orderId, productId, code));
        }
    }

    private String generateDigitalCode() {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 8; i++)
            code.append((char) (Math.random() * 26 + 'A'));
        return code.toString();
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

package com.gameshub.service.order;

import com.gameshub.enums.OrderPayment;
import com.gameshub.enums.OrderStatus;
import com.gameshub.exception.BadRequestException;
import com.gameshub.exception.InsufficientBalanceException;
import com.gameshub.exception.OutOfStockException;
import com.gameshub.exception.ResourceNotFoundException;
import com.gameshub.model.cart.DigitalCartDAO;
import com.gameshub.model.cart.PhysicalCartDAO;
import com.gameshub.model.order.DigitalCode;
import com.gameshub.model.order.DigitalOrderItemDAO;
import com.gameshub.model.order.OrderDAO;
import com.gameshub.model.order.PhysicalOrderItemDAO;
import com.gameshub.model.product.DigitalProductDAO;
import com.gameshub.model.product.PhysicalProductDAO;
import com.gameshub.model.user.BuyerDAO;
import com.gameshub.repository.order.DigitalCodeRepository;
import com.gameshub.repository.order.DigitalOrderItemRepository;
import com.gameshub.repository.order.OrderRepository;
import com.gameshub.repository.order.PhysicalOrderItemRepository;
import com.gameshub.service.cart.CartService;
import com.gameshub.service.product.ProductService;
import com.gameshub.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public List<OrderDAO> getOrders(int buyerId) {
        List<OrderDAO> orders =  orderRepository.findByBuyerId(buyerId).reversed();

        for (OrderDAO order : orders)
            for (DigitalOrderItemDAO item : order.getDigitalOrderItemDAOs()) {
                List<DigitalCode> digitalCodes = digitalCodeRepository.findById_OrderIdAndId_ProductId(order.getId(), item.getId().getProductId());
                List<String> codeStrings = digitalCodes.stream().map(digitalCode -> digitalCode.getId().getCode()).toList();
                item.setCodes(codeStrings);
            }
        return orders;
    }

    @Transactional
    public void orderPhysical(int buyerId, String paymentMethod) {
        List<PhysicalCartDAO> physicalCartDAOs = cartService.getPhysicalCartItems(buyerId);
        List<PhysicalOrderItemDAO> physicalOrderItemDAOs = preparePhysicalOrderItems(physicalCartDAOs);
        float orderPrice = calculateTotalPhysicalOrderPrice(physicalOrderItemDAOs);

        validateBuyerBalance(buyerId, paymentMethod, orderPrice);
        OrderDAO orderDAO = createOrder(buyerId, orderPrice, paymentMethod);
        updatePhysicalOrderItemsWithOrderId(physicalOrderItemDAOs, orderDAO.getId());
        cartService.deletePhysicalCartItems(buyerId);
        updateBuyerBalance(buyerId, paymentMethod, orderPrice);
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
                    0,
                    product.getId(),
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

    private void updatePhysicalProductsCounts(List<PhysicalOrderItemDAO> physicalOrderItemDAOs) {
        for (PhysicalOrderItemDAO physicalOrderItemDAO : physicalOrderItemDAOs)
            productService.decreasePhysicalProductCount(physicalOrderItemDAO.getId().getProductId(), physicalOrderItemDAO.getCount());
    }

    private void updatePhysicalSellersBalances(List<PhysicalOrderItemDAO> physicalOrderItemDAOs) {
        for (PhysicalOrderItemDAO physicalOrderItemDAO : physicalOrderItemDAOs) {
            int physicalProductId = physicalOrderItemDAO.getId().getProductId();
            PhysicalProductDAO physicalProductDAO = productService.getPhysicalProductByProductId(physicalProductId);
            int sellerId = physicalProductDAO.getSellerId();
            userService.increaseSellerBalance(sellerId, physicalOrderItemDAO.getTotalPrice());
        }
    }

    @Transactional
    public void orderDigital(int buyerId, String paymentMethod) {
        List<DigitalCartDAO> digitalCartDAOs = cartService.getDigitalCartItems(buyerId);
        List<DigitalOrderItemDAO> digitalOrderItemDAOs = prepareDigitalOrderItems(digitalCartDAOs);
        float orderPrice = calculateTotalDigitalOrderPrice(digitalOrderItemDAOs);

        validateBuyerBalance(buyerId, paymentMethod, orderPrice);
        OrderDAO orderDAO = createOrder(buyerId, orderPrice, paymentMethod);
        updateDigitalOrderItemsWithOrderId(digitalOrderItemDAOs, orderDAO.getId());
        cartService.deleteDigitalCartItems(buyerId);
        updateBuyerBalance(buyerId, paymentMethod, orderPrice);
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
                    0,
                    product.getId(),
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
            addDigitalCodes(orderId, item.getId().getProductId(), item.getCount());
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

    private void updateDigitalProductsCounts(List<DigitalOrderItemDAO> digitalOrderItemDAOs) {
        for (DigitalOrderItemDAO digitalOrderItemDAO : digitalOrderItemDAOs)
            productService.decreaseDigitalProductCount(digitalOrderItemDAO.getId().getProductId(), digitalOrderItemDAO.getCount());
    }

    private void updateDigitalSellersBalances(List<DigitalOrderItemDAO> digitalOrderItemDAOs) {
        for (DigitalOrderItemDAO digitalOrderItemDAO : digitalOrderItemDAOs) {
            int digitalProductId = digitalOrderItemDAO.getId().getProductId();
            DigitalProductDAO digitalProductDAO = productService.getDigitalProductByProductId(digitalProductId);
            int sellerId = digitalProductDAO.getSellerId();
            userService.increaseSellerBalance(sellerId, digitalOrderItemDAO.getTotalPrice());
        }
    }

    private OrderDAO createOrder(int buyerId, float orderPrice, String paymentMethod) {
        OrderDAO orderDAO = new OrderDAO(buyerId, LocalDate.now(), orderPrice, paymentMethod, OrderStatus.PROCESSING.name());
        orderRepository.save(orderDAO);
        return orderDAO;
    }

    private void updateBuyerBalance(int buyerId, String paymentMethod, float orderPrice) {
        if (!OrderPayment.WALLET.name().equals(paymentMethod))
            return;

        userService.decreaseBuyerBalance(buyerId, orderPrice);
    }

    private void validateBuyerBalance(int buyerId, String paymentMethod, float orderPrice) {
        if (OrderPayment.WALLET.name().equals(paymentMethod)) {
            BuyerDAO buyer = userService.getBuyerById(buyerId);
            if (buyer.getBalance() < orderPrice)
                throw new InsufficientBalanceException("Sorry, your wallet balance is insufficient for this transaction");
        }
    }

}

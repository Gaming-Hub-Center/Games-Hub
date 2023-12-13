package com.gameshub.model.order;

import com.gameshub.model.user.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.*;
import java.util.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "`Order`")
public class OrderDAO {

    public OrderDAO(BuyerDAO buyerDAO, LocalDate orderDate, float orderPrice, String orderStatus) {
        this.buyerDAO = buyerDAO;
        this.orderDate = orderDate;
        this.orderPrice = orderPrice;
        this.orderStatus = orderStatus;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderID")
    private int orderID;

    @ManyToOne
    @JoinColumn(name = "buyerID", referencedColumnName = "ID")
    private BuyerDAO buyerDAO;

    @Column(name = "orderdate")
    private LocalDate orderDate;

    @Column(name = "order_price")
    private float orderPrice;

    @Column(name = "order_status")
    private String orderStatus;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "orderID")
    private List<PhysicalOrderItemDAO> physicalOrderItemDAOs;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "orderID")
    private List<DigitalOrderItemDAO> digitalOrderItemDAOs;

}

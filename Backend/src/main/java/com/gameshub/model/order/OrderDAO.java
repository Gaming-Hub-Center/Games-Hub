package com.gameshub.model.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "`order`")
public class OrderDAO {

    public OrderDAO(int buyerId, LocalDate orderDate, float orderPrice, String paymentMethod, String orderStatus) {
        this.buyerId = buyerId;
        this.orderDate = orderDate;
        this.orderPrice = orderPrice;
        this.paymentMethod = paymentMethod;
        this.orderStatus = orderStatus;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Buyerid")
    private int buyerId;

    @Column(name = "Orderdate")
    private LocalDate orderDate;

    @Column(name = "Orderprice")
    private float orderPrice;

    @Column(name = "Paymentmethod")
    private String paymentMethod;

    @Column(name = "Orderstatus")
    private String orderStatus;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "Orderid")
    private List<PhysicalOrderItemDAO> physicalOrderItemDAOs;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "Orderid")
    private List<DigitalOrderItemDAO> digitalOrderItemDAOs;

}

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

    public OrderDAO(BuyerDAO buyerDAO, LocalDate orderDate, float orderPrice, String orderStatus) {
        this.buyerDAO = buyerDAO;
        this.orderDate = orderDate;
        this.orderPrice = orderPrice;
        this.orderStatus = orderStatus;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "Buyerid")
    private BuyerDAO buyerDAO;

    @Column(name = "Orderdate")
    private LocalDate orderDate;

    @Column(name = "Orderprice")
    private float orderPrice;

    @Column(name = "Orderstatus")
    private String orderStatus;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "Orderid")
    private List<PhysicalOrderItemDAO> physicalOrderItemDAOs;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "Orderid")
    private List<DigitalOrderItemDAO> digitalOrderItemDAOs;

}

package com.gameshub.model.order;

import com.gameshub.model.user.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "`Order`")
public class OrderDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderID")
    private int orderID;

    @ManyToOne
    @JoinColumn(name = "buyerID", referencedColumnName = "ID")
    private BuyerDAO buyerDAO;

    @Column(name = "orderDate")
    private Date orderDate;

    @Column(name = "order_price")
    private float orderPrice;

    @Column(name = "order_status")
    private String orderStatus;

    @OneToMany
    @JoinColumn(name = "orderID")
    private List<PhysicalOrderDAO> physicalOrderDAOs;

}

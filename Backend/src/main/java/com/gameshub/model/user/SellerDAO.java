package com.gameshub.model.user;

import jakarta.persistence.*;
import lombok.*;

import java.time.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "seller")
public class SellerDAO extends UserDAO {

    @Column(name = "Address")
    protected String address;

    @Column(name = "Balance")
    protected float balance;

    @Column(name = "Nationalid")
    private String nationalID;

    @Column(name = "Datejoined")
    private LocalDate dateJoined;

    @Column(name = "Sellerdescription")
    private String sellerDescription;

    @Column(name = "Vatregistrationnumber")
    private String vatRegistrationNumber;

    public SellerDAO(String name, String email, String password, String phone, String address, float balance, String nationalID, LocalDate dateJoined, String sellerDescription, String vatRegistrationNumber) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.balance = balance;
        this.nationalID = nationalID;
        this.dateJoined = dateJoined;
        this.sellerDescription = sellerDescription;
        this.vatRegistrationNumber = vatRegistrationNumber;
    }

}

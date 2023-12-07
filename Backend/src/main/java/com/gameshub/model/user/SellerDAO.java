package com.gameshub.model.user;

import jakarta.persistence.*;
import lombok.*;

import java.time.*;

@Data
@Entity
@Table(name = "`seller`")
@NoArgsConstructor
public class SellerDAO extends UserDAO {

    @Column(name = "NationalID")
    private String nationalID;

    @Column(name = "Date_Joined")
    private LocalDate dateJoined;

    @Column(name = "Sellerdescription")
    private String sellerDescription;

    @Column(name = "Vat_Registration_Number")
    private String vatRegistrationNumber;

    public SellerDAO(int id, String name, String email, String password, String phone, String address, float balance, String nationalID, LocalDate dateJoined, String sellerDescription, String vatRegistrationNumber) {
        this.id = id;
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

package com.gameshub.model.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "seller")
public class SellerDAO extends UserDAO {

<<<<<<< Updated upstream
=======
    public SellerDAO(String name, String email, String password, String phone, String address, float balance, String nationalId, LocalDate dateJoined, String sellerDescription, String vatRegistrationNumber) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.balance = balance;
        this.nationalId = nationalId;
        this.dateJoined = dateJoined;
        this.sellerDescription = sellerDescription;
        this.vatRegistrationNumber = vatRegistrationNumber;
    }

    @PostLoad
    private void setDefaultRole() {
        this.role = "SELLER";
    }

    @Column(name = "Address")
    protected String address;

    @Column(name = "Balance")
    protected float balance;

>>>>>>> Stashed changes
    @Column(name = "Nationalid")
    private String nationalId;

    @Column(name = "Datejoined")
    private LocalDate dateJoined;

    @Column(name = "Sellerdescription")
    private String sellerDescription;

    @Column(name = "Vatregistrationnumber")
    private String vatRegistrationNumber;

}

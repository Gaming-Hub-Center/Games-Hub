package com.gameshub.model.user;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "buyer")
public class BuyerDAO extends UserDAO {

    @Column(name = "Address")
    protected String address;

    @Column(name = "Balance")
    protected float balance;

    public BuyerDAO(String name, String email, String password, String phone, String address, float balance) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.balance = balance;
    }

}

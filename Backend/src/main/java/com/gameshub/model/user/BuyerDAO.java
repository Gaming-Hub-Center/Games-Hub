package com.gameshub.model.user;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "buyer")
@NoArgsConstructor
public class BuyerDAO extends UserDAO {

    public BuyerDAO(int id, String name, String email, String password, String phone, String address, float balance) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.balance = balance;
    }

    public BuyerDAO(String name, String email, String password, String phone, String address, float balance) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.balance = balance;
    }

}

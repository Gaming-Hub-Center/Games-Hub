package com.gameshub.Model.Users;

import jakarta.persistence.*;

@Entity
@Table(name = "buyer")
public class BuyerDAO extends UserDAO {

    public BuyerDAO() { }

    public BuyerDAO(int id, String name, String email, String password, String phone, String address, float balance) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.balance = balance;
    }

}
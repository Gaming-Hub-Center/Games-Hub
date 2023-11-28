package com.gameshub.Model.Users;

import javax.persistence.*;

@Entity
@Table(name = "buyer")
public class BuyerDAO extends UserDAO {

    public BuyerDAO() { }

    public BuyerDAO(String name, String email, String password, String phone, String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

}

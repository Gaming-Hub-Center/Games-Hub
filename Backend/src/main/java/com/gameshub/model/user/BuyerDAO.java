package com.gameshub.model.user;

import com.gameshub.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "buyer")
public class BuyerDAO extends UserDAO {

    public BuyerDAO(String name, String email, String password, String phone, String address, float balance) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.balance = balance;
    }

    @PostLoad
    private void setDefaultRole() {
        this.role = Role.BUYER.name();
    }

    @Column(name = "Address")
    protected String address;

    @Column(name = "Balance")
    protected float balance;

}

package com.gameshub.model.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class UserDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    protected int id;

    @Column(name = "Name")
    protected String name;

    @Column(name = "Email", unique = true)
    protected String email;

    @Column(name = "Password")
    protected String password;

    @Column(name = "Phone")
    protected String phone;

<<<<<<< Updated upstream
    @Column(name = "Address")
    protected String address;

    @Column(name = "Balance")
    protected float balance;
=======
    @Transient
    protected String role;
>>>>>>> Stashed changes

}

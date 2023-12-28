package com.gameshub.model.user;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "admin")
public class AdminDAO {
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
}

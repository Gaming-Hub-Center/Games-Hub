package com.gameshub.Model.Users;

import jakarta.persistence.*;

import java.time.*;

@Entity
@Table(name = "seller")
public class SellerDAO extends UserDAO {

    @Column(name = "NationalID")
    private String nationalID;

    @Column(name = "Date_Joined")
    private LocalDate dateJoined;

    @Column(name = "Sellerdescription")
    private String sellerDescription;

    @Column(name = "Vat_Registration_Number")
    private String vatRegistrationNumber;

    public SellerDAO() { }

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

    public String getNationalID() {
        return nationalID;
    }

    public void setNationalID(String nationalID) {
        this.nationalID = nationalID;
    }

    public LocalDate getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(LocalDate dateJoined) {
        this.dateJoined = dateJoined;
    }

    public String getSellerDescription() {
        return sellerDescription;
    }

    public void setSellerDescription(String sellerDescription) {
        this.sellerDescription = sellerDescription;
    }

    public String getVatRegistrationNumber() {
        return vatRegistrationNumber;
    }

    public void setVatRegistrationNumber(String vatRegistrationNumber) {
        this.vatRegistrationNumber = vatRegistrationNumber;
    }

}
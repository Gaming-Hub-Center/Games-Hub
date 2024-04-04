package com.gameshub.model.user;

import com.gameshub.enums.Role;
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
@Table(name = "admin")
public class AdminDAO extends UserDAO {

    public AdminDAO(String name, String email, String password, String phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    @PostLoad
    private void setDefaultRole() {
        this.role = Role.ADMIN.name();
    }

}

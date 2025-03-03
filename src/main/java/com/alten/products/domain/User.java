package com.alten.products.domain;

import com.alten.products.utils.SecureRandomIDGenerator;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User { //lombok ?
    @Id
    private Long id; // this.id = SecureRandomIDGenerator.generateID();

    private String username;

    private String firstname;

    private String email;

    private String password;

    public User() {}

    public User(String username, String firstname, String email, String password) {
        this.id = SecureRandomIDGenerator.generateID();
        this.username = username;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

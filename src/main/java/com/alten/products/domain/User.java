package com.alten.products.domain;

import com.alten.products.utils.SecureRandomIDGenerator;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    private Long id;
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
    public String getFirstname() {
        return firstname;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }

}

package com.minder.MoneyMinder.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class ResetPasswordTokenEntity {
    private final static long TOKEN_EXPIRATION_IN_MINUTES = 30;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String token;
    private String name;
    private String email;
    private LocalDateTime expirationDate;

    public ResetPasswordTokenEntity(String token, String email, String name) {
        this.token = token;
        this.email = email;
        this.name = name;
        this.expirationDate = LocalDateTime.now().plusMinutes(TOKEN_EXPIRATION_IN_MINUTES);
    }

    public ResetPasswordTokenEntity() {

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "ResetPasswordTokenEntity{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", expirationDate=" + expirationDate +
                '}';
    }
}

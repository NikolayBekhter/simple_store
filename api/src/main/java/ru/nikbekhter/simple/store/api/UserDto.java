package ru.nikbekhter.simple.store.api;

import java.math.BigDecimal;

public class UserDto {
    private Long id;
    private String email;
    private String password;
    private String username;
    private BigDecimal balance;
    private boolean active;

    public UserDto() {
    }

    public UserDto(Long id, String email, String password, String username, BigDecimal balance, boolean active) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.balance = balance;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}

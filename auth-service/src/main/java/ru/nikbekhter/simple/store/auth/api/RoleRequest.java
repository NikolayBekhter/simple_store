package ru.nikbekhter.simple.store.auth.api;

import lombok.Data;

@Data
public class RoleRequest {
    private String username;
    private String role;
}

package ru.nikbekhter.simple.store.auth.api;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private String username;
    private BigDecimal balance;
}

package ru.nikbekhter.simple.store.auth.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nikbekhter.simple.store.api.AppError;
import ru.nikbekhter.simple.store.auth.services.UsersProductsService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users_products")
public class UsersProductsController {
    private final UsersProductsService usersProductsService;

    @GetMapping
    public ResponseEntity<?> purchasedProduct(@RequestHeader(name = "username") String username, @PathVariable Long productId) {
        if (!usersProductsService.purchasedProduct(username, productId)) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Пользователь: " + username + " не приобретал продукт с id: " + productId), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(usersProductsService.purchasedProduct(username, productId));
    }

    @GetMapping("/save/{productId}")
    public void save(@RequestHeader(name = "username") String username, @PathVariable Long productId) {
        usersProductsService.save(username, productId);
    }
}

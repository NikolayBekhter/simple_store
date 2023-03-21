package ru.nikbekhter.simple.store.cart.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import ru.nikbekhter.simple.store.cart.api.CartDto;
import ru.nikbekhter.simple.store.cart.converters.CartConverter;
import ru.nikbekhter.simple.store.cart.services.CartService;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@Log4j2
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping("/add/{id}")
    public void addToCart(@RequestHeader(name = "username") String username, @PathVariable Long id) {
        cartService.add(username, id);
    }

    @GetMapping("/clear")
    public void clearCart(@RequestHeader(name = "username") String username) {
        cartService.clear(username);
    }

    @GetMapping("/remove/{id}")
    public void removeFromCart(@RequestHeader(name = "username") String username, @PathVariable Long id) {
        cartService.remove(username, id);
    }

    @GetMapping
    public CartDto getCurrentCart(@RequestHeader(name = "username") String username) {
        return cartConverter.entityToDto(cartService.getCurrentCart(username));
    }

    @GetMapping("/change_quantity")
    public void changeQuantity(@RequestHeader(name = "username") String username, @RequestParam Long productId, @RequestParam Integer delta) {
        cartService.changeQuantity(username, productId, delta);
    }

}

package ru.nikbekhter.simple.store.cart.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.nikbekhter.simple.store.api.ProductDto;
import ru.nikbekhter.simple.store.cart.integrations.ProductServiceIntegration;
import ru.nikbekhter.simple.store.cart.model.Cart;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Log4j2
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;

    @Value("${cart-service.cart-prefix}")
    private String cartPrefix;
    private Map<String, Cart> carts;

    @PostConstruct
    public void init() {
        carts = new HashMap<>();
    }

    public Cart getCurrentCart(String username) {
        String targetUuid = cartPrefix + username;
        if (!carts.containsKey(targetUuid)) {
            carts.put(targetUuid, new Cart());
        }
        return carts.get(targetUuid);
    }

    public void add(String username, Long productId) {
        ProductDto product = productServiceIntegration.getProductById(productId);
        getCurrentCart(username).add(product);
    }

    public void remove(String username, Long productId) {
        getCurrentCart(username).remove(productId);
    }

    public void clear(String username) {
        getCurrentCart(username).clear();
    }

    public void changeQuantity(String username, Long productId, Integer delta) {
        ProductDto product = productServiceIntegration.getProductById(productId);
        getCurrentCart(username).changeQuantity(product, delta);
    }
}

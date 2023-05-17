package ru.nikbekhter.simple.store.cart.model;

import lombok.Data;
import ru.nikbekhter.simple.store.api.ProductDto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {
    private List<CartItem> items;
    private BigDecimal totalPrice;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void add(ProductDto product) {
        if (product.getQuantity() != 0) {
            for (CartItem item :
                    items) {
                if (product.getId().equals(item.getProductId())) {
                    item.changeQuantity(1);
                    recalculate();
                    return;
                }
            }
            items.add(new CartItem(product.getId(), product.getTitle(), 1, product.getPrice(), product.getPrice()));
            recalculate();
        }
    }

    public void remove(Long productId) {
        if (items.removeIf(item -> item.getProductId().equals(productId))) {
            recalculate();
        }
    }

    public void clear() {
        items.clear();
        totalPrice = BigDecimal.ZERO;
    }

    private void recalculate() {
        totalPrice = BigDecimal.ZERO;
        for (CartItem item :
                items) {
            totalPrice = totalPrice.add(item.getPrice()).setScale(2, RoundingMode.HALF_UP);
        }
    }

    public void changeQuantity(ProductDto product, Integer delta) {
        for (CartItem item :
                items) {
            if (product.getId().equals(item.getProductId())) {
                item.changeQuantity(delta);
                if (item.getQuantity() == 0) {
                    remove(product.getId());
                    return;
                }
                recalculate();
                return;
            }
        }
        recalculate();
    }
}

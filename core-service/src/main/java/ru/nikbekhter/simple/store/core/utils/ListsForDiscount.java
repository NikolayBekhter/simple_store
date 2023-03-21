package ru.nikbekhter.simple.store.core.utils;

import lombok.Data;
import ru.nikbekhter.simple.store.core.entities.Product;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListsForDiscount {
    private List<Product> products;

    public ListsForDiscount() {
        this.products = new ArrayList<>();
    }

    public void add(Product product) {
        products.add(product);
    }

    public void remove(Long id) {
        products.removeIf(product -> product.getId().equals(id));
    }

    public void clear() {
        products.clear();
    }
}

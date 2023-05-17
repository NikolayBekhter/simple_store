package ru.nikbekhter.simple.store.core.servises;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nikbekhter.simple.store.api.DiscountDto;
import ru.nikbekhter.simple.store.core.entities.*;
import ru.nikbekhter.simple.store.core.repositories.DiscountRepository;
import ru.nikbekhter.simple.store.core.utils.ListsForDiscount;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiscountService {
    private final DiscountRepository repository;
    private final ProductService productService;
    private ListsForDiscount productsList;

    public void save(DiscountDto discountDto) {
        Discount discount = new Discount();
        discount.setDis(discountDto.getDis());
        discount.setProducts(getProductsList());
        discount.setStartDate(discountDto.getStartDate());
        discount.setExpiryDate(discountDto.getExpiryDate());
        repository.save(discount);
    }

    public void update(DiscountDto discountDto) {
        Discount discount = repository.findById(discountDto.getId())
                .orElseThrow(/*() -> new ResourceNotFoundException("Скидка с id: " + discountDto.getId() + " не найдена.")*/);
        if (discountDto.getDis() != 0) {
            discount.setDis(discountDto.getDis());
        }
        if (discountDto.getStartDate() != null) {
            discount.setStartDate(discountDto.getStartDate());
        }
        if (discountDto.getExpiryDate() != null) {
            discount.setExpiryDate(discountDto.getExpiryDate());
        }
        if (getProductsList() != null) {
            discount.setProducts(getProductsList());
        }
        repository.save(discount);
    }

    @PostConstruct
    public void init() {
        productsList = new ListsForDiscount();
    }

    public List<Product> getProductsList() {
        return productsList.getProducts();
    }

    public void add(Long id) {
        productsList.add(productService.findProductById(id));
    }

    public void remove(Long id) {
        productsList.remove(id);
    }

    public void clear() {
        productsList.clear();
    }
}

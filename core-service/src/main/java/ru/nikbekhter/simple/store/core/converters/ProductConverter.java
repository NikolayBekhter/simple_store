package ru.nikbekhter.simple.store.core.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nikbekhter.simple.store.core.api.ProductDto;
import ru.nikbekhter.simple.store.core.entities.Product;

@Service
@RequiredArgsConstructor
public class ProductConverter {

    public ProductDto entityToDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .title(product.getTitle())
                .description(product.getDescription())
                .organizationTitle(product.getOrganization().getTitle())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .discount(product.getDiscount())
                .review(product.getReview())
                .build();
    }

    public Product dtoToEntity(ProductDto productDto) {
        return Product.builder()
                .title(productDto.getTitle())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .quantity(productDto.getQuantity())
                .build();
    }
}

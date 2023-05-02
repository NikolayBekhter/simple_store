package ru.nikbekhter.simple.store.core.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nikbekhter.simple.store.api.ProductDto;
import ru.nikbekhter.simple.store.core.entities.Product;

@Service
@RequiredArgsConstructor
public class ProductConverter {

    public ProductDto entityToDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setTitle(product.getTitle());
        dto.setDescription(product.getDescription());
        dto.setOrganizationTitle(product.getOrganization().getTitle());
        dto.setPrice(product.getPrice());
        dto.setQuantity(product.getQuantity());
        dto.setConfirmed(product.isConfirmed());
        return dto;
    }

    public Product dtoToEntity(ProductDto productDto) {
        return Product.builder()
                .id(productDto.getId())
                .title(productDto.getTitle())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .quantity(productDto.getQuantity())
                .isConfirmed(productDto.isConfirmed())
                .build();
    }
}

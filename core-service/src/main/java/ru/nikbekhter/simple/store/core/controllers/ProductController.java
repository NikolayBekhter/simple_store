package ru.nikbekhter.simple.store.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.nikbekhter.simple.store.core.api.OrganizationDto;
import ru.nikbekhter.simple.store.core.api.PageDto;
import ru.nikbekhter.simple.store.core.api.ProductDto;
import ru.nikbekhter.simple.store.core.api.ResourceNotFoundException;
import ru.nikbekhter.simple.store.core.converters.ProductConverter;
import ru.nikbekhter.simple.store.core.entities.Product;
import ru.nikbekhter.simple.store.core.servises.OrganizationService;
import ru.nikbekhter.simple.store.core.servises.ProductService;
import ru.nikbekhter.simple.store.core.utils.MyQueue;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;
    private MyQueue<Product> productQueue = new MyQueue<>();

    @GetMapping
    public PageDto<ProductDto> getProducts(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "min_price", required = false) Integer minPrice,
            @RequestParam(name = "max_price", required = false) Integer maxPrice,
//            @RequestParam(name = "keyword_part", required = false) String keywordPart,
            @RequestParam(name = "title_part", required = false) String titlePart
    ) {
        if (page < 1) {
            page = 1;
        }

        Page<ProductDto> jpaPage = productService.find(minPrice, maxPrice, titlePart, page).map(
                productConverter::entityToDto
        );
        PageDto<ProductDto> out = new PageDto<>();
        out.setPage(jpaPage.getNumber());
//        out.setItems(jpaPage.getContent());
        out.setTotalPages(jpaPage.getTotalPages());
        List<ProductDto> productDtos = new ArrayList<>();
        for (ProductDto productDto : jpaPage.getContent()) {
            if (productDto.isConfirmed()) {
                productDtos.add(productDto);
            }
        }
        out.setItems(productDtos);
        return out;
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable  Long id) {
        return productConverter.entityToDto(productService.findProductById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto createOrUpdateProduct(@RequestBody ProductDto productDto) throws ResourceNotFoundException {
        return productConverter.entityToDto(productService.saveOrUpdate(productDto));
    }

    @GetMapping("/not_confirmed")
    public ProductDto notConfirmed() throws ResourceNotFoundException {
        return productConverter.entityToDto(productService.notConfirmed());
    }

    @GetMapping("/confirm/{title}")
    public void confirm(@PathVariable String title) {
        productService.confirm(title);
    }

}

package ru.nikbekhter.simple.store.core.servises;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.nikbekhter.simple.store.core.api.ProductDto;
import ru.nikbekhter.simple.store.core.api.ResourceNotFoundException;
import ru.nikbekhter.simple.store.core.entities.Organization;
import ru.nikbekhter.simple.store.core.entities.Product;
import ru.nikbekhter.simple.store.core.repositories.ProductRepository;
import ru.nikbekhter.simple.store.core.repositories.specifications.ProductSpecifications;
import ru.nikbekhter.simple.store.core.utils.IdentityMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository repository;
    private final OrganizationService organizationService;
    private IdentityMap identityMap = new IdentityMap();

    public Page<Product> find(Integer minPrice, Integer maxPrice, String titlePart, String orgTitlePart, Integer page) {
        Specification<Product> spec = Specification.where(null);
        if (minPrice != null) {
            spec = spec.and(ProductSpecifications.costGreaterOrEqualsThan(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductSpecifications.costLessOrEqualsThan(maxPrice));
        }
        if (titlePart != null) {
            spec = spec.and(ProductSpecifications.titleLike(titlePart));
        }
        if (orgTitlePart != null) {
            spec = spec.and(ProductSpecifications.orgTitleLike(orgTitlePart));
        }
//        if (keywordPart != null) {
//            spec = spec.and(ProductSpecifications.keywordLike(keywordPart));
//        }
        return repository.findAll(spec, PageRequest.of(page - 1, 5));
    }

    public Product findProductById(Long id) {
        Product product = this.identityMap.getProductMap(id);
        if (product != null) {
            log.info("Product found in the Map");
            return product;
        } else {
            // Try to find product in the database
            product = repository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Продукт не найден, id: " + id));
            if (product != null) {
                this.identityMap.addProductMap(product);
                log.info("Product found in DB.");
                return product;
            }
        }
        return null;
    }

    public Product saveOrUpdate(ProductDto productDto) {
        if (productDto.getId() != null) {
            Product productFromBd = repository.findById(productDto.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Продукт не найден, id: " + productDto.getId()));
            if (productDto.getTitle() != null) {
                productFromBd.setTitle(productDto.getTitle());
            }
            if (productDto.getDescription() != null) {
                productFromBd.setDescription(productDto.getDescription());
            }
            if (productDto.getOrganizationTitle() != null) {
                Organization organization = organizationService.findByTitleIgnoreCase(productDto.getOrganizationTitle());
                productFromBd.setOrganization(organization);
            }
            if (productDto.getPrice() != null) {
                productFromBd.setPrice(productDto.getPrice());
            }
            if (productDto.getQuantity() != 0) {
                productFromBd.setQuantity(productDto.getQuantity());
            }
            return repository.save(productFromBd);
        } else {
            Product product = Product.builder()
                    .id(productDto.getId())
                    .title(productDto.getTitle())
                    .description(productDto.getDescription())
                    .organization(organizationService.findByTitleIgnoreCase(productDto.getOrganizationTitle()))
                    .price(productDto.getPrice())
                    .quantity(productDto.getQuantity())
                    .build();
            return repository.save(product);
        }

    }
}

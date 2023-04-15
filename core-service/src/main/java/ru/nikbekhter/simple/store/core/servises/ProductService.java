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
import ru.nikbekhter.simple.store.core.utils.MyQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository repository;
    private final OrganizationService organizationService;
    private IdentityMap identityMap = new IdentityMap();
    private MyQueue<Product> productQueue = new MyQueue<>();

    public Page<Product> find(Integer minPrice, Integer maxPrice, String titlePart, Integer page) {
        Specification<Product> spec = Specification.where(null);
        if (minPrice != null) {
            spec = spec.and(ProductSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductSpecifications.priceLessOrEqualsThan(maxPrice));
        }
        if (titlePart != null) {
            spec = spec.and(ProductSpecifications.titleLike(titlePart));
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

    public Product saveOrUpdate(ProductDto productDto) throws ResourceNotFoundException {
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
                productFromBd.setQuantity(productFromBd.getQuantity() + productDto.getQuantity());
            }
            return repository.save(productFromBd);
        } else {
            Organization organization = organizationService.findByTitleIgnoreCase(productDto.getOrganizationTitle());
//            System.out.println(organization);
            if (organization == null) {
                throw new ResourceNotFoundException("Организация не прошла модерацию, попробуйте добавить продукт позже.");
            }
            Product product = new Product();
            product.setId(productDto.getId());
            product.setTitle(productDto.getTitle());
            product.setDescription(productDto.getDescription());
            product.setOrganization(organization);
            product.setPrice(productDto.getPrice());
            product.setConfirmed(false);
            product.setQuantity(productDto.getQuantity());
            return repository.save(product);
        }

    }

    public Product notConfirmed() throws ResourceNotFoundException {
        if (productQueue.isEmpty()) {
            List<Product> notConfirmList = repository.findAllByIsConfirmed(false);
            if (notConfirmList.isEmpty()) {
                throw new ResourceNotFoundException("Не подтвержденных продуктов больше нет.");
            }
            for (Product product : notConfirmList) {
                productQueue.enqueue(product);
            }
        }
        return productQueue.dequeue();
    }

    public void confirm(String title) {
        Product product = repository.findByTitleIgnoreCase(title).get();
        product.setConfirmed(true);
        repository.save(product);
    }

    public void changeQuantity(Product product) throws ResourceNotFoundException {
        Product productFromDB = repository.findById(product.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Продукт не найден, id: " + product.getId()));
        if (productFromDB.getQuantity() >= product.getQuantity()) {
            productFromDB.setQuantity(productFromDB.getQuantity() - product.getQuantity());
            repository.save(productFromDB);
        } else {
            throw new ResourceNotFoundException("Недостаточное колличество продукта, id: " + product.getId());
        }
    }
}

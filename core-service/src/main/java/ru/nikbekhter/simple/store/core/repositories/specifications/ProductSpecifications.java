package ru.nikbekhter.simple.store.core.repositories.specifications;


import org.springframework.data.jpa.domain.Specification;
import ru.nikbekhter.simple.store.core.entities.Product;

public class ProductSpecifications {
    public static Specification<Product> costGreaterOrEqualsThan(Integer minPrice) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    public static Specification<Product> costLessOrEqualsThan(Integer maxPrice) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
    }

    public static Specification<Product> titleLike(String titlePart) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", titlePart));
    }

//    public static Specification<Product> keywordLike(String keywordPart) {
//        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("keyword"), String.format("%%%s%%", keywordPart));
//    }

    public static Specification<Product> orgTitleLike(String organizationTitlePart) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("organizationTitle"), String.format("%%%s%%", organizationTitlePart));
    }

}

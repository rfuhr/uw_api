package br.com.ultraworks.erp.core.util;

import org.springframework.data.jpa.domain.Specification;

public class UniqueValidationSpecification<T> {
    public static <T> Specification<T> isFieldEqualTo(String fieldName, Object value) {
        return (root, query, criteriaBuilder) -> {
            if (value == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get(fieldName), value);
        };
    }
}

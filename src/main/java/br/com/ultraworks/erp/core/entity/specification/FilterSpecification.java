package br.com.ultraworks.erp.core.entity.specification;

import org.springframework.data.jpa.domain.Specification;

import br.com.ultraworks.erp.core.dto.OpcaoFiltro;
import br.com.ultraworks.erp.core.exception.BusinessException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class FilterSpecification<T> {

	public static <T> Specification<T> createRelatedSpecification(String chave, OpcaoFiltro opcao) {
		String[] partes = chave.split("\\.");
		if (partes.length == 2) {
		    return (root, query, criteriaBuilder) -> {
		        Join<Object, Object> join = root.join(partes[0], JoinType.INNER);
		        if (opcao.getTipo().equals("text")) {
		        	return createStringBuilder(opcao, join.get(partes[1]), criteriaBuilder);
		        } else if ("integer".equals(opcao.getTipo())) {
					return createIntegerBuilder(opcao, join.get(partes[1]), criteriaBuilder);
				} else if ("boolean".equals(opcao.getTipo())) {
					return createBooleanBuilder(opcao, join.get(partes[1]), criteriaBuilder);
				} else {
					throw new BusinessException("Tipo de campo não implementado para o filtro.");
				}
//		        return criteriaBuilder.like(join.get(partes[1]), opcao.getValue());
		    };
		} else {
		    throw new BusinessException("Campo de relação inválido. Deve ser no formato 'tabela.campo'.");
		}
	}
	
	public static <T> Specification<T> createStringSpecification(String chave, OpcaoFiltro filtro) {
        return (root, query, builder) -> {
        	return createStringBuilder(filtro, root.get(chave), builder);
        };
    }

	private static <T> Predicate createStringBuilder(OpcaoFiltro filtro, Path<String> x, CriteriaBuilder builder) {
		if ("contains".equals(filtro.getMatchMode())) {
		    return builder.like(builder.lower(x), "%" + filtro.getValue().toLowerCase() + "%");
		} else if ("notContains".equals(filtro.getMatchMode())) {
		    return builder.notLike(builder.lower(x), "%" + filtro.getValue().toLowerCase() + "%");
		} else if ("startsWith".equals(filtro.getMatchMode())) {
		    return builder.like(builder.lower(x), filtro.getValue().toLowerCase() + "%");
		} else if ("endsWith".equals(filtro.getMatchMode())) {
		    return builder.like(builder.lower(x), "%" + filtro.getValue().toLowerCase());
		} else if ("notEquals".equals(filtro.getMatchMode())) {
		    return builder.notEqual(builder.lower(x), filtro.getValue().toLowerCase());
		} else {
		    return builder.equal(builder.lower(x), filtro.getValue().toLowerCase());
		}
	}
	
	public static <T> Specification<T> createIntegerSpecification(String chave, OpcaoFiltro filtro) {
        return (root, query, builder) -> {
        	return createIntegerBuilder(filtro, root.get(chave), builder);
        };
    }

	private static <T> Predicate createIntegerBuilder(OpcaoFiltro filtro, Path<Integer> x, CriteriaBuilder builder) {
		if ("contains".equals(filtro.getMatchMode())) {
		    return builder.like(x.as(String.class), "%" + filtro.getValue() + "%");
		} else if ("notContains".equals(filtro.getMatchMode())) {
		    return builder.notLike(x.as(String.class), "%" + filtro.getValue() + "%");
		} else if ("startsWith".equals(filtro.getMatchMode())) {
		    return builder.greaterThanOrEqualTo(x, Integer.parseInt(filtro.getValue()));
		} else if ("endsWith".equals(filtro.getMatchMode())) {
		    return builder.lessThanOrEqualTo(x, Integer.parseInt(filtro.getValue()));
		} else if ("notEquals".equals(filtro.getMatchMode())) {
		    return builder.notEqual(x, Integer.parseInt(filtro.getValue()));
		} else {
		    return builder.equal(x, Integer.parseInt(filtro.getValue()));
		}
	}
	
	public static <T> Specification<T> createBooleanSpecification(String chave, OpcaoFiltro filtro) {
        return (root, query, builder) -> {
        	return createBooleanBuilder(filtro, root.get(chave), builder);
        };
    }

	private static <T> Predicate createBooleanBuilder(OpcaoFiltro filtro, Path<Boolean> x, CriteriaBuilder builder) {
		return builder.equal(x, Boolean.valueOf(filtro.getValue()));
	}
	
	public static <T> Specification<T> createEnumSpecification(String chave, OpcaoFiltro filtro) {
        return (root, query, builder) -> {
        	return createEnumBuilder(filtro, root.get(chave), builder);
        };
    }

	private static <T> Predicate createEnumBuilder(OpcaoFiltro filtro, Path<String> x, CriteriaBuilder builder) {
		if ("contains".equals(filtro.getMatchMode())) {
		    return builder.like(builder.lower(x), "%" + filtro.getValue().toLowerCase() + "%");
		} else if ("notContains".equals(filtro.getMatchMode())) {
		    return builder.notLike(builder.lower(x), "%" + filtro.getValue().toLowerCase() + "%");
		} else if ("startsWith".equals(filtro.getMatchMode())) {
		    return builder.like(builder.lower(x), filtro.getValue().toLowerCase() + "%");
		} else if ("endsWith".equals(filtro.getMatchMode())) {
		    return builder.like(builder.lower(x), "%" + filtro.getValue().toLowerCase());
		} else if ("notEquals".equals(filtro.getMatchMode())) {
		    return builder.notEqual(builder.lower(x), filtro.getValue().toLowerCase());
		} else {
		    return builder.equal(builder.lower(x), filtro.getValue().toLowerCase());
		}
	}
	
}

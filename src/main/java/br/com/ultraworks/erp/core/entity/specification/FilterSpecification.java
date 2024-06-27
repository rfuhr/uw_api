package br.com.ultraworks.erp.core.entity.specification;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import br.com.ultraworks.erp.core.dto.OpcaoFiltro;
import br.com.ultraworks.erp.core.exception.BusinessException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;

public class FilterSpecification<T> {

	public static <T> Specification<T> createRelatedSpecification(String chave, OpcaoFiltro opcao) {
		String[] partes = chave.split("\\.");
		if (partes.length < 2) {
			throw new BusinessException(
					"Campo de relação inválido. Deve ser no formato 'tabela.campo' ou 'tabelaA.tabelaB.campo'.");
		}

		return (root, query, criteriaBuilder) -> {
			// Inicializa o join com a raiz
			From<?, ?> join = root;

			// Realiza os joins de acordo com as partes, exceto o último, que é o campo
			for (int i = 0; i < partes.length - 1; i++) {
				join = join.join(partes[i], JoinType.INNER);
			}

			// O último elemento é o campo
			Path<?> campoPath = join.get(partes[partes.length - 1]);

			// Chama o método apropriado com o tipo correto
			switch (opcao.getTipo()) {
			case "text":
				@SuppressWarnings("unchecked")
				Path<String> stringPath = (Path<String>) campoPath;
				return createStringBuilder(opcao, stringPath, criteriaBuilder);
			case "integer":
				@SuppressWarnings("unchecked")
				Path<Integer> integerPath = (Path<Integer>) campoPath;
				return createIntegerBuilder(opcao, integerPath, criteriaBuilder);
			case "boolean":
				@SuppressWarnings("unchecked")
				Path<Boolean> booleanPath = (Path<Boolean>) campoPath;
				return createBooleanBuilder(opcao, booleanPath, criteriaBuilder);
			default:
				throw new BusinessException("Tipo de campo não implementado para o filtro.");
			}
		};
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

	public static <T> Specification<T> createInIntegerSpecification(String chave, List<Long> ids) {
		return (root, query, builder) -> {
			return root.get(chave).in(ids);
		};
	}
	
	public static <T> Specification<T> createDecimalSpecification(String chave, OpcaoFiltro filtro) {
		return (root, query, builder) -> {
			return createDecimalBuilder(filtro, root.get(chave), builder);
		};
	}

	private static <T> Predicate createDecimalBuilder(OpcaoFiltro filtro, Path<Integer> x, CriteriaBuilder builder) {
		if ("contains".equals(filtro.getMatchMode())) {
			return builder.like(x.as(String.class), "%" + filtro.getValue() + "%");
		} else if ("notContains".equals(filtro.getMatchMode())) {
			return builder.notLike(x.as(String.class), "%" + filtro.getValue() + "%");
		} else if ("startsWith".equals(filtro.getMatchMode())) {
			return builder.greaterThanOrEqualTo(x, Integer.parseInt(filtro.getValue()));
		} else if ("endsWith".equals(filtro.getMatchMode())) {
			return builder.lessThanOrEqualTo(x, Integer.parseInt(filtro.getValue()));
		} else if ("notEquals".equals(filtro.getMatchMode())) {
			return builder.notEqual(x, Double.parseDouble(filtro.getValue()));
		} else {
			return builder.equal(x, Double.parseDouble(filtro.getValue()));
		}
	}
}

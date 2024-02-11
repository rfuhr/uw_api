package br.com.ultraworks.erp.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.InvalidParameterException;
import java.util.Map;
import java.util.Scanner;

import org.springframework.data.jpa.domain.Specification;

import br.com.ultraworks.erp.core.dto.LazyParams;
import br.com.ultraworks.erp.core.dto.OpcaoFiltro;
import br.com.ultraworks.erp.core.entity.specification.FilterSpecification;
import br.com.ultraworks.erp.core.exception.BusinessException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;

public class SQLUtils {

	public static String obterQuery(final String string) {
		StringBuilder result = new StringBuilder();
		// Get file from resources folder

		try (InputStream is = SQLUtils.class.getClassLoader().getResourceAsStream("queries/" + string + ".sql");
				Scanner scanner = new Scanner(is, StandardCharsets.UTF_8.name())) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				result.append(line).append(" ");
			}
		} catch (IOException e) {
			throw new InvalidParameterException("Não foi possível abrir o arquivo de query passado por parâmetro.");
		}
		return result.toString();
	}

	public static <T> Specification<T> buildQueryByFilters(LazyParams params) {
		Specification<T> specification = null;

		
		if (params.getFilters() != null) {
			for (Map.Entry<String, OpcaoFiltro> entrada : params.getFilters().entrySet()) {
				OpcaoFiltro opcao = entrada.getValue();

				if (entrada != null && opcao.getValue() != null && !opcao.getValue().isEmpty()
						&& !opcao.getValue().isBlank()) {
					String chave = opcao.getFieldFilter() != null ? opcao.getFieldFilter() : entrada.getKey();
					Specification<T> filtroSpecification;

					if (chave.contains(".")) {
	                    // Verifica se o campo é uma relação, por exemplo, "uf.sigla"
	                    filtroSpecification = FilterSpecification.createRelatedSpecification(chave, opcao);
					} else if ("text".equals(opcao.getTipo())) {
						filtroSpecification = FilterSpecification.createStringSpecification(chave, opcao);
					} else if ("integer".equals(opcao.getTipo())) {
						filtroSpecification = FilterSpecification.createIntegerSpecification(chave, opcao);
					} else if ("boolean".equals(opcao.getTipo())) {
						filtroSpecification = FilterSpecification.createBooleanSpecification(chave, opcao);
					} else {
						throw new BusinessException("Tipo de campo não implementado para o filtro.");
					}

					if (specification == null) {
						specification = filtroSpecification;
					} else {
						specification = specification.and(filtroSpecification);
					}
				}
			}
		}
//		if (params.getId() != null) {
//			Specification<T> filtroSpecification;
//			filtroSpecification = SQLUtils.criarSpecificationPorId(params.getId());
//			if (specification == null) {
//				specification = filtroSpecification;
//			} else {
//				specification = specification.or(filtroSpecification);
//			}
//		}

		return specification;
	}

	public static <T> Specification<T> criarSpecificationPorId(Long id) {
        return (root, query, builder) -> {
            // Aqui você deve substituir "id" pelo nome do atributo na sua entidade que representa o ID
            return builder.equal(root.get("id"), id); // Filtrar pelo ID igual a 3
        };
    }
}

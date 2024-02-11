package br.com.ultraworks.erp.core.service;

import java.lang.reflect.Field;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.core.annotation.UniqueValidation;
import br.com.ultraworks.erp.core.annotation.UniqueValidationGroup;
import br.com.ultraworks.erp.core.exception.UnicidadeException;
import br.com.ultraworks.erp.core.exception.ValidationError;
import br.com.ultraworks.erp.core.exception.ValidationErrorResponse;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Query;
import jakarta.persistence.Table;

@Service
public class UniqueValidationService {

	private final EntityManager entityManager;

	public UniqueValidationService(@Lazy EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public <T> void verificarUnicidade(T entity, boolean create) throws Exception {
		UniqueValidationGroup uniqueGroupAnnotation = entity.getClass().getAnnotation(UniqueValidationGroup.class);
		ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse();

		if (uniqueGroupAnnotation != null) {
			UniqueValidation[] uniqueAnnotations = uniqueGroupAnnotation.value();
			for (UniqueValidation uniqueAnnotation : uniqueAnnotations) {
				String[] fieldsToCheck = uniqueAnnotation.fields();
				String label = uniqueAnnotation.label();

				// Construa a cláusula WHERE da consulta nativa com base nos campos
				// especificados
				String whereClause = "";
				for (String field : fieldsToCheck) {
					whereClause += getColumnName(field, entity.getClass()) + " = :" + field + " AND ";
				}
				if (!create)
					whereClause += obterNomeAtributoId(entity.getClass()) + " <> :id";
				else
					whereClause = whereClause.replaceFirst("(?i)\\s*AND\\s*$", "");
				
				// Crie a consulta nativa
				String sql = "SELECT COUNT(*) FROM " + getTableName(entity.getClass()) + " WHERE " + whereClause;

				// Crie a consulta com o EntityManager
				Query query = entityManager.createNativeQuery(sql);

				// Defina os parâmetros
				for (String field : fieldsToCheck) {
					Object value;
					try {
						Field declaredField = entity.getClass().getDeclaredField(field);
						if (isOtherClassAndHasId(declaredField)) {
							value = getIdValueOfOtherClass(entity, declaredField);
						} else {
							declaredField.setAccessible(true);
							value = declaredField.get(entity);
						}
					} catch (Exception e) {
						// Lidar com exceções adequadamente
						value = null;
					}
					query.setParameter(field, value);
				}
				if (!create)
					query.setParameter("id", obterValorAtributoId(entity));
				// Execute a consulta
				Long count = 0L;
				try {
					count = ((Number) query.getSingleResult()).longValue();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				if (count > 0) {
					// Lidar com a violação de unicidade, lançar exceção ou realizar ação
					// apropriada.
					validationErrorResponse.getErrors().add(new ValidationError(label,
							"Os campos " + String.join(", ", fieldsToCheck) + " não são únicos."));
				}
			}
		}
		if (!validationErrorResponse.getErrors().isEmpty()) {
			throw new UnicidadeException(validationErrorResponse,
					"Ocorreu um erro na validação de unicidade das informações");
		}
	}

	public String getColumnName(String fieldName, Class<?> clazz) {
		try {
			Field field = clazz.getDeclaredField(fieldName);
			if (isOtherClassAndHasId(field)) {
				return getIdFieldOfOtherClass(field);
			} else {
				Column column = field.getAnnotation(Column.class);
				JoinColumn joinColumn = field.getAnnotation(JoinColumn.class);

				if (column != null) {
					return column.name();
				} else if (joinColumn != null) {
					return joinColumn.name();
				} else {
					return fieldName;
				}
			}
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getTableName(Class<?> clazz) {
		Table table = clazz.getAnnotation(Table.class);
		if (table != null) {
			return table.name();
		} else {
			Entity entity = clazz.getAnnotation(Entity.class);
			if (entity != null) {
				return clazz.getSimpleName();
			}
		}
		return null;
	}

	public boolean isOtherClassAndHasId(Field declaredField) {
		Class<?> fieldType = declaredField.getType();
		// Verifica se o tipo do campo é outra classe
		if (!fieldType.isPrimitive()) {
			// Verifica se a classe tem um atributo chamado "id"
			for (Field field : fieldType.getDeclaredFields()) {
				if (field.getName().equals("id")) {
					return true;
				}
			}
		}
		return false;
	}

	public String getIdFieldOfOtherClass(Field declaredField) throws NoSuchFieldException {
		Class<?> fieldType = declaredField.getType();
		// Verifica se o tipo do campo é outra classe
		if (!fieldType.isPrimitive()) {
			// Tenta obter o campo "id" da outra classe
			Field idField = fieldType.getDeclaredField("id");
			Column column = idField.getAnnotation(Column.class);
			JoinColumn joinColumn = idField.getAnnotation(JoinColumn.class);

			if (column != null) {
				return column.name();
			} else if (joinColumn != null) {
				return joinColumn.name();
			} else {
				return idField.getName();
			}
		}
		return null;
	}

	public Object getIdValueOfOtherClass(Object entity, Field declaredField)
			throws IllegalAccessException, NoSuchFieldException {
		Class<?> fieldType = declaredField.getType();
		// Verifica se o tipo do campo é outra classe
		if (!fieldType.isPrimitive()) {
			// Tenta obter o campo "id" da outra classe
			Field idField = fieldType.getDeclaredField("id");
			idField.setAccessible(true);
			// Obtém o valor do campo "id" no objeto entity
			Object idValue = idField.get(entity);
			return idValue;
		}
		return null;
	}

	public static String obterNomeAtributoId(Class<?> classe) {
		// Obtém todos os campos da classe
		Field[] campos = classe.getDeclaredFields();

		// Itera sobre os campos
		for (Field campo : campos) {
			// Verifica se o campo está anotado com @Id
			if (campo.isAnnotationPresent(Id.class)) {
				// Retorna o nome do campo
				return campo.getName();
			}
		}

		// Retorna null se nenhum campo com a anotação @Id for encontrado
		return null;
	}

	public static Long obterValorAtributoId(Object objeto) throws IllegalAccessException {
		// Obtém a classe do objeto
		Class<?> classe = objeto.getClass();

		// Obtém todos os campos da classe
		Field[] campos = classe.getDeclaredFields();

		// Itera sobre os campos
		for (Field campo : campos) {
			// Verifica se o campo está anotado com @Id
			if (campo.isAnnotationPresent(Id.class)) {
				// Torna o campo acessível, se não for público
				campo.setAccessible(true);

				// Retorna o valor do campo no objeto fornecido
				return (Long) campo.get(objeto);
			}
		}

		// Retorna null se nenhum campo com a anotação @Id for encontrado
		return null;
	}
}

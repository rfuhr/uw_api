package br.com.ultraworks.erp.core.generics;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.core.UWRepository;
import br.com.ultraworks.erp.core.dto.LazyParams;
import br.com.ultraworks.erp.core.entity.specification.FilterSpecification;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.EntityMapper;
import br.com.ultraworks.erp.core.util.SQLUtils;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Component
public class GenericService<T, ID, D> {

	protected UWRepository<T, ID> repository;
	private EntityMapper<T, D> mapper;

	public GenericService(UWRepository<T, ID> repository, EntityMapper<T, D> mapper) {
		super();
		this.repository = repository;
		this.mapper = mapper;
	}

	public Optional<T> getById(ID id) {
		return repository.findById(id);
	}

	public List<T> getAll() {
		return repository.findAll();
	}

	public Page<T> getAllPaginada(LazyParams params) {
		List<Long> ids = new ArrayList<>();

		Page<T> resultados = getResultadosPaginado(params, ids);

		return resultados;
	}

	public Page<T> getAllPaginadaFilterIds(LazyParams params, List<Long> ids) {
		if (ids == null)
			ids = new ArrayList<>();

		Page<T> resultados = getResultadosPaginado(params, ids);

		return resultados;
	}

	private boolean hasField(String fieldName) {
		// Obtém a classe do tipo T
		Class<?> clazz = (Class<?>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		try {
			// Tenta obter o campo pela reflexão
			Field field = clazz.getDeclaredField(fieldName);
			return true; // O campo existe
		} catch (NoSuchFieldException e) {
			return false; // O campo não existe
		}
	}

	private Page<T> getResultadosPaginado(LazyParams params, List<Long> ids) {

		Specification<T> specification = SQLUtils.buildQueryByFilters(params, ids);

		if (params.isNoShowGeneral() && hasField("general")) {
			Specification<T> noShowGeneralSpec = FilterSpecification.createNoShowGeneralSpecification();

			specification = (specification != null) ? specification.and(noShowGeneralSpec) : noShowGeneralSpec;
		}

		Sort sort = Sort.unsorted();
		if (params.getSortField() != null) {
			String sortField = params.getSortField();
			Sort.Direction sortOrder = (params.getSortOrder() == 1) ? Sort.Direction.ASC : Sort.Direction.DESC;
			sort = Sort.by(sortOrder, sortField);
		}
		int rows = params.getRows() == null ? 10 : params.getRows();
		int pagina = params.getFirst() == 0 ? 0 : Math.abs(params.getFirst() / rows);
		Pageable pageable = PageRequest.of(pagina, rows, sort);
		Page<T> resultados = repository.findAll(specification, pageable);

		if (resultados.getContent() != null && params.getId() != null) {
			Optional<T> registroEspecifico = resultados.getContent().stream().filter(item -> {
				Optional<Object> fieldValue = getFieldValue(item, "id");

				return fieldValue.isPresent() && fieldValue.get().equals(params.getId());
			}).findFirst();
			if (!registroEspecifico.isPresent()) {
				Optional<T> res = repository.findById((ID) params.getId());
				if (res.isPresent()) {
					List<T> content = new ArrayList<>();
					content.add(res.get());
					content.addAll(resultados.getContent());
					Page<T> newResultados = new PageImpl<>(content, resultados.getPageable(),
							resultados.getTotalElements());
					return newResultados;
				}
				;
			}
		}
		return resultados;
	}

	public T save(T entity) {
		entity = repository.save(entity);
		return entity;
	}

	public void delete(ID id) {
		repository.findById(id).orElseThrow(RegisterNotFoundException::new);
		repository.deleteById(id);
	}

	private static <T> Optional<Object> getFieldValue(T obj, String fieldName) {
		try {
			// Obtém a classe do objeto
			Class<?> clazz = obj.getClass();

			// Tenta obter o campo diretamente
			try {
				Field field = clazz.getDeclaredField(fieldName);
				field.setAccessible(true); // Garante que o campo seja acessível, mesmo se for privado
				return Optional.ofNullable(field.get(obj));
			} catch (NoSuchFieldException e) {
				// Se não encontrar o campo diretamente, tenta encontrar um método getter
				String getterName = "get" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
				Method getter = clazz.getMethod(getterName);
				return Optional.ofNullable(getter.invoke(obj));
			}
		} catch (Exception e) {
			e.printStackTrace(); // Trate ou registre exceções conforme necessário
			return Optional.empty();
		}
	}

	public void delete(List<ID> ids) {
		ids.stream().forEach(id -> repository.findById(id).orElseThrow(RegisterNotFoundException::new));
		repository.deleteAllById(ids);
	}

}

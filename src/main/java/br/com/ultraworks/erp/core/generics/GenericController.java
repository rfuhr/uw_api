package br.com.ultraworks.erp.core.generics;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.ultraworks.erp.core.dto.LazyParams;
import br.com.ultraworks.erp.core.dto.ResultPage;
import br.com.ultraworks.erp.core.exception.BusinessException;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.EntityMapper;
import br.com.ultraworks.erp.core.util.ResponseUtil;
import br.com.ultraworks.erp.core.validators.CustomValidator;
import jakarta.transaction.Transactional;


public class GenericController<T, ID, D> {

	protected GenericService<T, ID, D> service;
	protected EntityMapper<T, D> mapper;
	
	@Autowired
	private CustomValidator<D> dtoValidator;
	
	@Autowired
    private PlatformTransactionManager transactionManager;

	public GenericController(GenericService<T, ID, D> service, EntityMapper<T, D> mapper) {
		super();
		this.service = service;
		this.mapper = mapper;
	}
	
	@GetMapping("{id}")
	public ResponseEntity<D> getById(@PathVariable ID id) {
		Optional<T> entity = service.getById(id);
		if (entity.isPresent()) {
			return ResponseEntity.ok(mapper.toDto(entity.get()));
		} else {
			throw  new RegisterNotFoundException("Não encontrado registro");
		}
	};
	
	@GetMapping
	public ResponseEntity<?> getAll() {
		List<D> entities = service.getAll()
		        .stream()
		        .map(mapper::toDto)
		        .collect(Collectors.toList());
				return ResponseEntity.ok(entities);
	}
	
	@PostMapping("/paginada")
	public ResponseEntity<ResultPage<D>> getAll(@RequestBody LazyParams params) {
		Page<T> resultados = service.getAllPaginada(params);
		
		ResultPage<D> resultado = new ResultPage<D>(mapper.toDto(resultados.getContent()),
				resultados.getTotalElements(), resultados.getPageable().getPageNumber(),
				resultados.getPageable().getPageSize(), resultados.getTotalPages());
		return ResponseEntity.ok(resultado);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<D> create(@RequestBody D dto) {
//		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
//        def.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
//        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        
//        TransactionStatus status = transactionManager.getTransaction(def);
		
		dtoValidator.validate(dto);
		T entity = mapper.toNewEntity(dto);
		service.save(entity);
//		transactionManager.commit(status);
		D dto2 = mapper.toDto(entity);
		return ResponseUtil.wrapOrNotFound(Optional.of(dto2));
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<D> update(@RequestBody D dto) {
		dtoValidator.validate(dto);
		T entity = mapper.toUpdateEntity(dto);
		entity = service.save(entity);
		return ResponseUtil.wrapOrNotFound(Optional.of(mapper.toDto(entity)));
	}
	
	@DeleteMapping("{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable ID id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	private Long getId(T entity) {
    	try {
    		Class<?> dtoClass = entity.getClass();
    		Method getIdMethod;
			getIdMethod = dtoClass.getMethod("getId");
			Object result = getIdMethod.invoke(entity);
			Long id = (Long) result;
			return id;
		} catch (Exception e) {
			new BusinessException("Não foi possível obter id do objeto");
		}
    	return 0L;
        
    }
}

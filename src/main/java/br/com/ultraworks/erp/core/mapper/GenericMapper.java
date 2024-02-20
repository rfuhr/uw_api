package br.com.ultraworks.erp.core.mapper;

import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ultraworks.erp.core.exception.BusinessException;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;

public abstract class GenericMapper<Entity, DTO> implements EntityMapper<Entity, DTO> {
	
    protected final JpaRepository<Entity, Long> repository;
    private final Supplier<Entity> entitySupplier;
    private final Supplier<DTO> dtoSupplier;

    public GenericMapper(JpaRepository<Entity, Long> repository, Supplier<Entity> entitySupplier, Supplier<DTO> dtoSupplier) {
        this.repository = repository;
        this.entitySupplier = entitySupplier;
        this.dtoSupplier = dtoSupplier;
    }
    
    @Override
    public Entity toNewEntity(DTO dto) {
        Entity entity = entitySupplier.get();
        setNullToId(dto);
        setValuesToEntity(dto, entity);
        return entity;
    }

    @Override
	public Entity toUpdateEntity(DTO dto) { 
    	Entity entity = repository.findById(getId(dto)).orElseThrow(() -> new RegisterNotFoundException("Não encontrado registro para id informado"));
		setValuesToEntity(dto, entity);
		
		return entity;
	}
    
    @Override
	public Entity toEntity(DTO dto) {
    	Entity entity = null;
    	Long id = getId(dto);
		if (id != null)
    		entity = repository.findById(getId(dto)).orElse(entitySupplier.get());
    	else 
    		entity = entitySupplier.get();
		setValuesToEntity(dto, entity);
		
		return entity;
	}
    
    @Override
	public DTO toDto(Entity entity) { 
    	DTO dto = dtoSupplier.get();
		setValuesToDto(entity, dto);
		
		return dto;
	}
    
    @Override
	public List<Entity> toNewEntity(List<DTO> dtoList) { 
		return dtoList.stream().map(this::toNewEntity).collect(Collectors.toList());
	}
    
    @Override
	public List<Entity> toUpdateEntity(List<DTO> dtoList) { 
		return dtoList.stream().map(this::toUpdateEntity).collect(Collectors.toList());
	}

    @Override
	public List<Entity> toEntity(List<DTO> dtoList) { 
		return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
	}
    
    @Override
	public List<DTO> toDto(List<Entity> entityList) { 
		return entityList.stream().map(this::toDto).collect(Collectors.toList());
	}
    
    protected abstract void setValuesToEntity(DTO dto, Entity entity);

    protected abstract void setValuesToDto(Entity entity, DTO dto);
    
    private Long getId(DTO dto) {
    	try {
    		Class<?> dtoClass = dto.getClass();
    		Method getIdMethod;
			getIdMethod = dtoClass.getMethod("getId");
			Object result = getIdMethod.invoke(dto);
			Long id = (Long) result;
			return id;
		} catch (Exception e) {
			new BusinessException("Não foi possível obter id do objeto");
		}
    	return 0L;
        
    }
    
    private void setNullToId(DTO dto) {
        try {
            Class<?> dtoClass = dto.getClass();
            Method setIdMethod = dtoClass.getMethod("setId", Long.class);
            setIdMethod.invoke(dto, (Long) null);
        } catch (Exception e) {
            throw new BusinessException("Não foi possível definir o id do objeto", e);
        }
    }
}

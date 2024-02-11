package br.com.ultraworks.erp.core.mapper;

import java.util.List;

public interface EntityMapper<Entity, DTO> {

	Entity toNewEntity(DTO dto);
    
	Entity toUpdateEntity(DTO dto);
	
	Entity toEntity(DTO dto);

    DTO toDto(Entity entity);

    
    List<Entity> toNewEntity(List<DTO> dtoList);
    
    List<Entity> toUpdateEntity(List<DTO> dtoList);
    
    List<Entity> toEntity(List<DTO> dtoList);

    List<DTO> toDto(List<Entity> entityList);

}

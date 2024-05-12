package br.com.ultraworks.erp.api.estoque.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.estoque.domain.grupocontabil.GrupoContabil;
import br.com.ultraworks.erp.api.estoque.domain.grupocontabil.GrupoContabilDTO;
import br.com.ultraworks.erp.api.estoque.mapper.GrupoContabilMapper;
import br.com.ultraworks.erp.api.estoque.repository.GrupoContabilRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class GrupoContabilService extends GenericService<GrupoContabil, Long, GrupoContabilDTO> {

	@Autowired
	public GrupoContabilService(GrupoContabilRepository repository, GrupoContabilMapper mapper) {
		super(repository, mapper);
	}

}
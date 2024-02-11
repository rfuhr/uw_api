package br.com.ultraworks.erp.api.tabela.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.tabela.domain.estadocivil.EstadoCivil;
import br.com.ultraworks.erp.api.tabela.domain.estadocivil.EstadoCivilDTO;
import br.com.ultraworks.erp.api.tabela.mapper.EstadoCivilMapper;
import br.com.ultraworks.erp.api.tabela.repository.EstadoCivilRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class EstadoCivilService extends GenericService<EstadoCivil, Long, EstadoCivilDTO> {

	@Autowired
	public EstadoCivilService(EstadoCivilRepository repository, EstadoCivilMapper mapper) {
		super(repository, mapper);
	}

}

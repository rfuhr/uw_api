package br.com.ultraworks.erp.api.financeiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.financeiro.domain.agencia.Agencia;
import br.com.ultraworks.erp.api.financeiro.domain.agencia.AgenciaDTO;
import br.com.ultraworks.erp.api.financeiro.mapper.AgenciaMapper;
import br.com.ultraworks.erp.api.financeiro.repository.AgenciaRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class AgenciaService extends GenericService<Agencia, Long, AgenciaDTO> {

	@Autowired
	public AgenciaService(AgenciaRepository repository, AgenciaMapper mapper) {
		super(repository, mapper);
	}
}
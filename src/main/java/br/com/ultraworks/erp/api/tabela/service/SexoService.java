package br.com.ultraworks.erp.api.tabela.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.tabela.domain.sexo.Sexo;
import br.com.ultraworks.erp.api.tabela.domain.sexo.SexoDTO;
import br.com.ultraworks.erp.api.tabela.mapper.SexoMapper;
import br.com.ultraworks.erp.api.tabela.repository.SexoRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class SexoService extends GenericService<Sexo, Long, SexoDTO> {

	@Autowired
	public SexoService(SexoRepository repository, SexoMapper mapper) {
		super(repository, mapper);
	}

}
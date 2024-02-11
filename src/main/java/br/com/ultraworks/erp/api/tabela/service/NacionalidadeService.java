package br.com.ultraworks.erp.api.tabela.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.tabela.domain.nacionalidade.Nacionalidade;
import br.com.ultraworks.erp.api.tabela.domain.nacionalidade.NacionalidadeDTO;
import br.com.ultraworks.erp.api.tabela.mapper.NacionalidadeMapper;
import br.com.ultraworks.erp.api.tabela.repository.NacionalidadeRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class NacionalidadeService extends GenericService<Nacionalidade, Long, NacionalidadeDTO> {

	@Autowired
	public NacionalidadeService(NacionalidadeRepository repository, NacionalidadeMapper mapper) {
		super(repository, mapper);
	}

}
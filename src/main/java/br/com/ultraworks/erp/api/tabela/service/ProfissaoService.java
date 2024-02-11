package br.com.ultraworks.erp.api.tabela.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.tabela.domain.profissao.Profissao;
import br.com.ultraworks.erp.api.tabela.domain.profissao.ProfissaoDTO;
import br.com.ultraworks.erp.api.tabela.mapper.ProfissaoMapper;
import br.com.ultraworks.erp.api.tabela.repository.ProfissaoRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ProfissaoService extends GenericService<Profissao, Long, ProfissaoDTO> {

	@Autowired
	public ProfissaoService(ProfissaoRepository repository, ProfissaoMapper mapper) {
		super(repository, mapper);
	}

}

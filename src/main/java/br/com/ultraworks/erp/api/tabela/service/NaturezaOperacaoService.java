package br.com.ultraworks.erp.api.tabela.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.tabela.domain.naturezaOperacao.NaturezaOperacao;
import br.com.ultraworks.erp.api.tabela.domain.naturezaOperacao.NaturezaOperacaoDTO;
import br.com.ultraworks.erp.api.tabela.mapper.NaturezaOperacaoMapper;
import br.com.ultraworks.erp.api.tabela.repository.NaturezaOperacaoRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class NaturezaOperacaoService extends GenericService<NaturezaOperacao, Long, NaturezaOperacaoDTO> {

	@Autowired
	public NaturezaOperacaoService(NaturezaOperacaoRepository repository, NaturezaOperacaoMapper mapper) {
		super(repository, mapper);
	}

}
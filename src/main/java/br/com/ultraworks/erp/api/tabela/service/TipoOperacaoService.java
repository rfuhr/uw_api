package br.com.ultraworks.erp.api.tabela.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.tabela.domain.tipooperacao.TipoOperacao;
import br.com.ultraworks.erp.api.tabela.domain.tipooperacao.TipoOperacaoDTO;
import br.com.ultraworks.erp.api.tabela.mapper.TipoOperacaoMapper;
import br.com.ultraworks.erp.api.tabela.repository.TipoOperacaoRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class TipoOperacaoService extends GenericService<TipoOperacao, Long, TipoOperacaoDTO> {

	@Autowired
	public TipoOperacaoService(TipoOperacaoRepository repository, TipoOperacaoMapper mapper) {
		super(repository, mapper);
	}

}

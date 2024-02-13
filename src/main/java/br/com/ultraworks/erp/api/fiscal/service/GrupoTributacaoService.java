package br.com.ultraworks.erp.api.fiscal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.fiscal.domain.grupotributacao.GrupoTributacao;
import br.com.ultraworks.erp.api.fiscal.domain.grupotributacao.GrupoTributacaoDTO;
import br.com.ultraworks.erp.api.fiscal.mapper.GrupoTributacaoMapper;
import br.com.ultraworks.erp.api.fiscal.repository.GrupoTributacaoRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class GrupoTributacaoService extends GenericService<GrupoTributacao, Long, GrupoTributacaoDTO> {

	@Autowired
	public GrupoTributacaoService(GrupoTributacaoRepository repository, GrupoTributacaoMapper mapper) {
		super(repository, mapper);
	}

}
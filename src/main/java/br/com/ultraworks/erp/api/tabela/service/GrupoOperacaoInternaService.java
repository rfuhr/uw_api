package br.com.ultraworks.erp.api.tabela.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.tabela.domain.grupooperacaointerna.GrupoOperacaoInterna;
import br.com.ultraworks.erp.api.tabela.domain.grupooperacaointerna.GrupoOperacaoInternaDTO;
import br.com.ultraworks.erp.api.tabela.mapper.GrupoOperacaoInternaMapper;
import br.com.ultraworks.erp.api.tabela.repository.GrupoOperacaoInternaRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class GrupoOperacaoInternaService extends GenericService<GrupoOperacaoInterna, Long, GrupoOperacaoInternaDTO> {

	@Autowired
	public GrupoOperacaoInternaService(GrupoOperacaoInternaRepository repository, GrupoOperacaoInternaMapper mapper) {
		super(repository, mapper);
	}

}
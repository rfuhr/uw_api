package br.com.ultraworks.erp.api.tabela.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.financeiro.repository.TipoTituloRepository;
import br.com.ultraworks.erp.api.tabela.domain.historicopadrao.HistoricoPadrao;
import br.com.ultraworks.erp.api.tabela.domain.historicopadrao.HistoricoPadraoDTO;
import br.com.ultraworks.erp.api.tabela.mapper.HistoricoPadraoMapper;
import br.com.ultraworks.erp.api.tabela.repository.HistoricoPadraoRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class HistoricoPadraoService extends GenericService<HistoricoPadrao, Long, HistoricoPadraoDTO> {

	@Autowired
	public HistoricoPadraoService(HistoricoPadraoRepository repository, HistoricoPadraoMapper mapper) {
		super(repository, mapper);
	}

	public int getProximoCodigo() {
		return ((HistoricoPadraoRepository) repository).getProximoCodigo();
	}
}
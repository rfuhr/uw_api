package br.com.ultraworks.erp.api.financeiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.financeiro.domain.condicaopagamento.CondicaoPagamento;
import br.com.ultraworks.erp.api.financeiro.domain.condicaopagamento.CondicaoPagamentoDTO;
import br.com.ultraworks.erp.api.financeiro.mapper.CondicaoPagamentoMapper;
import br.com.ultraworks.erp.api.financeiro.repository.CondicaoPagamentoRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class CondicaoPagamentoService extends GenericService<CondicaoPagamento, Long, CondicaoPagamentoDTO> {

	@Autowired
	public CondicaoPagamentoService(CondicaoPagamentoRepository repository, CondicaoPagamentoMapper mapper) {
		super(repository, mapper);
	}

	public int getProximoCodigo() {
		return ((CondicaoPagamentoRepository) repository).getProximoCodigo();
	}

}
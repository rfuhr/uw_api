package br.com.ultraworks.erp.api.financeiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.financeiro.domain.negociacao.NegociacaoFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.negociacao.NegociacaoFinanceiraDTO;
import br.com.ultraworks.erp.api.financeiro.mapper.NegociacaoFinanceiraMapper;
import br.com.ultraworks.erp.api.financeiro.repository.NegociacaoFinanceiraRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import br.com.ultraworks.erp.core.util.NossoNumeroGenerator;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class NegociacaoFinanceiraService extends GenericService<NegociacaoFinanceira, Long, NegociacaoFinanceiraDTO> {

	@Autowired
	public NegociacaoFinanceiraService(NegociacaoFinanceiraRepository repository, NegociacaoFinanceiraMapper mapper) {
		super(repository, mapper);
	}

	public NegociacaoFinanceira save(NegociacaoFinanceira entity) {
		throw new UnsupportedOperationException("Este método não pode ser usado neste serviço específico.");
	}

	@Transactional
	public NegociacaoFinanceira inserirNegociacao(NegociacaoFinanceira negociacaoFinanceira) {

		negociacaoFinanceira.setId(null);
		negociacaoFinanceira.setNossoNumero(NossoNumeroGenerator.gerarNossoNumero());
		negociacaoFinanceira = repository.saveAndFlush(negociacaoFinanceira);
		return negociacaoFinanceira;
	}

}
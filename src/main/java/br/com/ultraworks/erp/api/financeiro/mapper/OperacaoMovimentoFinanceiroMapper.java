package br.com.ultraworks.erp.api.financeiro.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.financeiro.domain.operacaomovimentofinanceiro.OperacaoMovimentoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.operacaomovimentofinanceiro.OperacaoMovimentoFinanceiroDTO;
import br.com.ultraworks.erp.api.financeiro.repository.OperacaoMovimentoFinanceiroRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class OperacaoMovimentoFinanceiroMapper
		extends GenericMapper<OperacaoMovimentoFinanceiro, OperacaoMovimentoFinanceiroDTO> {

	public OperacaoMovimentoFinanceiroMapper(OperacaoMovimentoFinanceiroRepository repository) {
		super(repository, OperacaoMovimentoFinanceiro::new, OperacaoMovimentoFinanceiroDTO::new);
	}

	@Override
	protected void setValuesToEntity(OperacaoMovimentoFinanceiroDTO dto, OperacaoMovimentoFinanceiro entity) {
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
	}

	@Override
	protected void setValuesToDto(OperacaoMovimentoFinanceiro entity, OperacaoMovimentoFinanceiroDTO dto) {
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
	}
}

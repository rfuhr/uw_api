package br.com.ultraworks.erp.api.financeiro.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.financeiro.domain.operacaofinanceira.OperacaoFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.operacaofinanceira.OperacaoFinanceiraDTO;
import br.com.ultraworks.erp.api.financeiro.repository.OperacaoAcessoriaFinanceiraRepository;
import br.com.ultraworks.erp.api.financeiro.repository.OperacaoFinanceiraRepository;
import br.com.ultraworks.erp.api.financeiro.repository.OperacaoMovimentoFinanceiroRepository;
import br.com.ultraworks.erp.api.financeiro.repository.TipoOperacaoFinanceiraRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class OperacaoFinanceiraMapper extends GenericMapper<OperacaoFinanceira, OperacaoFinanceiraDTO> {

	private TipoOperacaoFinanceiraRepository tipoOperacaoFinanceiraRepository;
	private OperacaoMovimentoFinanceiroRepository operacaoMovimentoFinanceiroRepository;
	private OperacaoAcessoriaFinanceiraRepository operacaoAcessoriaFinanceiraRepository;

	public OperacaoFinanceiraMapper(OperacaoFinanceiraRepository repository,
			TipoOperacaoFinanceiraRepository tipoOperacaoFinanceiraRepository,
			OperacaoMovimentoFinanceiroRepository operacaoMovimentoFinanceiroRepository,
			OperacaoAcessoriaFinanceiraRepository operacaoAcessoriaFinanceiraRepository) {
		super(repository, OperacaoFinanceira::new, OperacaoFinanceiraDTO::new);
		this.tipoOperacaoFinanceiraRepository = tipoOperacaoFinanceiraRepository;
		this.operacaoMovimentoFinanceiroRepository = operacaoMovimentoFinanceiroRepository;
		this.operacaoAcessoriaFinanceiraRepository = operacaoAcessoriaFinanceiraRepository;
	}

	@Override
	protected void setValuesToEntity(OperacaoFinanceiraDTO dto, OperacaoFinanceira entity) {
		entity.setId(dto.getId());
		entity.setTipoOperacaoFinanceira(tipoOperacaoFinanceiraRepository.findById(dto.getTipoOperacaoFinanceiraId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado tipo operação financeira com id " + dto.getTipoOperacaoFinanceiraId())));
		entity.setOperacaoMovimentoFinanceiro(
				operacaoMovimentoFinanceiroRepository.findById(dto.getOperacaoMovimentoFinanceiroId()).orElseThrow(
						() -> new RegisterNotFoundException("Não encontrado tipo operação movimento financeiro com id "
								+ dto.getOperacaoMovimentoFinanceiroId())));
		entity.setOperacaoAcessoriaFinanceira(
				operacaoAcessoriaFinanceiraRepository.findById(dto.getOperacaoAcessoriaFinanceiraId()).orElseThrow(
						() -> new RegisterNotFoundException("Não encontrado tipo operação acessória financeira com id "
								+ dto.getOperacaoAcessoriaFinanceiraId())));

	}

	@Override
	protected void setValuesToDto(OperacaoFinanceira entity, OperacaoFinanceiraDTO dto) {
		dto.setId(entity.getId());
		dto.setTipoOperacaoFinanceiraId(entity.getTipoOperacaoFinanceira().getId());
		dto.setOperacaoMovimentoFinanceiroId(entity.getOperacaoAcessoriaFinanceira().getId());
		dto.setOperacaoAcessoriaFinanceiraId(entity.getOperacaoAcessoriaFinanceira().getId());
	}
}

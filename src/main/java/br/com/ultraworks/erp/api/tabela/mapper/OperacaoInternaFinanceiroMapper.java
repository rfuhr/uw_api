package br.com.ultraworks.erp.api.tabela.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.financeiro.repository.IndiceFinanceiroRepository;
import br.com.ultraworks.erp.api.tabela.domain.operacaointernafinanceiro.OperacaoInternaFinanceiro;
import br.com.ultraworks.erp.api.tabela.domain.operacaointernafinanceiro.OperacaoInternaFinanceiroDTO;
import br.com.ultraworks.erp.api.tabela.repository.OperacaoInternaFinanceiroRepository;
import br.com.ultraworks.erp.api.tabela.repository.OperacaoInternaRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class OperacaoInternaFinanceiroMapper
		extends GenericMapper<OperacaoInternaFinanceiro, OperacaoInternaFinanceiroDTO> {

	private OperacaoInternaRepository operacaoInternaRepository;
	private IndiceFinanceiroRepository indiceFinanceiroRepository;

	public OperacaoInternaFinanceiroMapper(OperacaoInternaFinanceiroRepository repository,
			OperacaoInternaRepository operacaoInternaRepository,
			IndiceFinanceiroRepository indiceFinanceiroRepository) {
		super(repository, OperacaoInternaFinanceiro::new, OperacaoInternaFinanceiroDTO::new);
		this.operacaoInternaRepository = operacaoInternaRepository;
		this.indiceFinanceiroRepository = indiceFinanceiroRepository;
	}

	@Override
	protected void setValuesToEntity(OperacaoInternaFinanceiroDTO dto, OperacaoInternaFinanceiro entity) {
		entity.setId(dto.getId());
		if (dto.getOperacaoInternaId() != null) {
			entity.setOperacaoInterna(operacaoInternaRepository.findById(dto.getOperacaoInternaId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado operação interna com id " + dto.getOperacaoInternaId())));
		}
		if (dto.getIndiceFinanceiroPadraoId() != null) {
			entity.setIndiceFinanceiroPadrao(indiceFinanceiroRepository.findById(dto.getIndiceFinanceiroPadraoId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado índice financeiro com id " + dto.getIndiceFinanceiroPadraoId())));
		}
	}

	@Override
	protected void setValuesToDto(OperacaoInternaFinanceiro entity, OperacaoInternaFinanceiroDTO dto) {
		dto.setId(entity.getId());
		dto.setOperacaoInternaId(entity.getOperacaoInterna().getId());
		if (entity.getIndiceFinanceiroPadrao() != null) {
			dto.setIndiceFinanceiroPadraoId(entity.getIndiceFinanceiroPadrao().getId());
			dto.setIndiceFinanceiroPadraoNome(entity.getIndiceFinanceiroPadrao().getNome());
		}
	}
}

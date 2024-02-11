package br.com.ultraworks.erp.api.financeiro.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.financeiro.domain.operacaoacessoriafinanceira.OperacaoAcessoriaFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.operacaoacessoriafinanceira.OperacaoAcessoriaFinanceiraDTO;
import br.com.ultraworks.erp.api.financeiro.repository.OperacaoAcessoriaFinanceiraRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class OperacaoAcessoriaFinanceiraMapper
		extends GenericMapper<OperacaoAcessoriaFinanceira, OperacaoAcessoriaFinanceiraDTO> {

	public OperacaoAcessoriaFinanceiraMapper(OperacaoAcessoriaFinanceiraRepository repository) {
		super(repository, OperacaoAcessoriaFinanceira::new, OperacaoAcessoriaFinanceiraDTO::new);
	}

	@Override
	protected void setValuesToEntity(OperacaoAcessoriaFinanceiraDTO dto, OperacaoAcessoriaFinanceira entity) {
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
	}

	@Override
	protected void setValuesToDto(OperacaoAcessoriaFinanceira entity, OperacaoAcessoriaFinanceiraDTO dto) {
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
	}
}

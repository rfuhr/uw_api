package br.com.ultraworks.erp.api.financeiro.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.financeiro.domain.tipooperacaofinanceira.TipoOperacaoFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.tipooperacaofinanceira.TipoOperacaoFinanceiraDTO;
import br.com.ultraworks.erp.api.financeiro.repository.TipoOperacaoFinanceiraRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class TipoOperacaoFinanceiraMapper extends GenericMapper<TipoOperacaoFinanceira, TipoOperacaoFinanceiraDTO> {

	public TipoOperacaoFinanceiraMapper(TipoOperacaoFinanceiraRepository repository) {
		super(repository, TipoOperacaoFinanceira::new, TipoOperacaoFinanceiraDTO::new);
	}

	@Override
	protected void setValuesToEntity(TipoOperacaoFinanceiraDTO dto, TipoOperacaoFinanceira entity) {
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
		entity.setSigla(dto.getSigla());
	}

	@Override
	protected void setValuesToDto(TipoOperacaoFinanceira entity, TipoOperacaoFinanceiraDTO dto) {
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		dto.setSigla(entity.getSigla());
	}
}

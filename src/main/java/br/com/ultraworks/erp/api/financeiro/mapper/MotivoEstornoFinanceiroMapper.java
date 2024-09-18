package br.com.ultraworks.erp.api.financeiro.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.financeiro.domain.motivoestornofinanceiro.MotivoEstornoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.motivoestornofinanceiro.MotivoEstornoFinanceiroDTO;
import br.com.ultraworks.erp.api.financeiro.repository.MotivoEstornoFinanceiroRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class MotivoEstornoFinanceiroMapper extends GenericMapper<MotivoEstornoFinanceiro, MotivoEstornoFinanceiroDTO> {

	public MotivoEstornoFinanceiroMapper(MotivoEstornoFinanceiroRepository repository) {
		super(repository, MotivoEstornoFinanceiro::new, MotivoEstornoFinanceiroDTO::new);
    }

	@Override
	protected void setValuesToEntity(MotivoEstornoFinanceiroDTO dto, MotivoEstornoFinanceiro entity) {
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
		entity.setSigla(dto.getSigla());
	}

	@Override
	protected void setValuesToDto(MotivoEstornoFinanceiro entity, MotivoEstornoFinanceiroDTO dto) {
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		dto.setSigla(entity.getSigla());
	}
}


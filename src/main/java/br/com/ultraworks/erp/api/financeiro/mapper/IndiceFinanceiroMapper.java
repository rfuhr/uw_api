package br.com.ultraworks.erp.api.financeiro.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.financeiro.domain.indicefinanceiro.IndiceFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.indicefinanceiro.IndiceFinanceiroDTO;
import br.com.ultraworks.erp.api.financeiro.repository.IndiceFinanceiroRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class IndiceFinanceiroMapper extends GenericMapper<IndiceFinanceiro, IndiceFinanceiroDTO> {

	public IndiceFinanceiroMapper(IndiceFinanceiroRepository repository) {
		super(repository, IndiceFinanceiro::new, IndiceFinanceiroDTO::new);
	}

	@Override
	protected void setValuesToEntity(IndiceFinanceiroDTO dto, IndiceFinanceiro entity) {
		entity.setId(dto.getId());
		entity.setCodigo(dto.getCodigo());
		entity.setNome(dto.getNome());
		entity.setSigla(dto.getSigla());
		entity.setDataInicioVigencia(dto.getDataInicioVigencia());
		entity.setDataFinalVigencia(dto.getDataFinalVigencia());
	}

	@Override
	protected void setValuesToDto(IndiceFinanceiro entity, IndiceFinanceiroDTO dto) {
		dto.setId(entity.getId());
		dto.setCodigo(entity.getCodigo());
		dto.setNome(entity.getNome());
		dto.setSigla(entity.getSigla());
		dto.setDataInicioVigencia(entity.getDataInicioVigencia());
		dto.setDataFinalVigencia(entity.getDataFinalVigencia());
	}
}

package br.com.ultraworks.erp.api.financeiro.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.financeiro.domain.grupofinanceiro.GrupoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.grupofinanceiro.GrupoFinanceiroDTO;
import br.com.ultraworks.erp.api.financeiro.repository.GrupoFinanceiroRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class GrupoFinanceiroMapper extends GenericMapper<GrupoFinanceiro, GrupoFinanceiroDTO> {

	public GrupoFinanceiroMapper(GrupoFinanceiroRepository repository) {
		super(repository, GrupoFinanceiro::new, GrupoFinanceiroDTO::new);
	}

	@Override
	protected void setValuesToEntity(GrupoFinanceiroDTO dto, GrupoFinanceiro entity) {
		entity.setId(dto.getId());
		entity.setCodigo(dto.getCodigo());
		entity.setNome(dto.getNome());
		entity.setSigla(dto.getSigla());
	}

	@Override
	protected void setValuesToDto(GrupoFinanceiro entity, GrupoFinanceiroDTO dto) {
		dto.setId(entity.getId());
		dto.setCodigo(entity.getCodigo());
		dto.setNome(entity.getNome());
		dto.setSigla(entity.getSigla());
	}
}

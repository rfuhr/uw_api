package br.com.ultraworks.erp.api.financeiro.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.financeiro.domain.planoclassificacaofinanceira.PlanoClassificacaoFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.planoclassificacaofinanceira.PlanoClassificacaoFinanceiraDTO;
import br.com.ultraworks.erp.api.financeiro.repository.PlanoClassificacaoFinanceiraRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class PlanoClassificacaoFinanceiraMapper
		extends GenericMapper<PlanoClassificacaoFinanceira, PlanoClassificacaoFinanceiraDTO> {

	public PlanoClassificacaoFinanceiraMapper(PlanoClassificacaoFinanceiraRepository repository) {
		super(repository, PlanoClassificacaoFinanceira::new, PlanoClassificacaoFinanceiraDTO::new);
	}

	@Override
	protected void setValuesToEntity(PlanoClassificacaoFinanceiraDTO dto, PlanoClassificacaoFinanceira entity) {
		entity.setId(dto.getId());
		entity.setCodigo(dto.getCodigo());
		entity.setNome(dto.getNome());
		entity.setNivel(dto.getNivel());
		entity.setSintetica(dto.isSintetica());

		if (dto.getContaSuperiorId() != null) {
			entity.setContaSuperior(
					repository.findById(dto.getContaSuperiorId()).orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado conta superior com id " + dto.getContaSuperiorId())));
		}
	}

	@Override
	protected void setValuesToDto(PlanoClassificacaoFinanceira entity, PlanoClassificacaoFinanceiraDTO dto) {
		dto.setId(entity.getId());
		dto.setCodigo(entity.getCodigo());
		dto.setNome(entity.getNome());
		dto.setNivel(entity.getNivel());
		dto.setSintetica(entity.isSintetica());
		if (entity.getContaSuperior() != null) {
			dto.setContaSuperiorId(entity.getContaSuperior().getId());
			dto.setContaSuperiorCodigo(entity.getContaSuperior().getCodigo());
			dto.setContaSuperiorNome(entity.getContaSuperior().getNome());
		}
	}
}

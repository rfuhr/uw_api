package br.com.ultraworks.erp.api.tabela.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.tabela.domain.grandezaMedida.GrandezaMedida;
import br.com.ultraworks.erp.api.tabela.domain.unidademedida.UnidadeMedida;
import br.com.ultraworks.erp.api.tabela.domain.unidademedida.UnidadeMedidaDTO;
import br.com.ultraworks.erp.api.tabela.repository.UnidadeMedidaRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class UnidadeMedidaMapper extends GenericMapper<UnidadeMedida, UnidadeMedidaDTO> {

	public UnidadeMedidaMapper(UnidadeMedidaRepository repository) {
		super(repository, UnidadeMedida::new, UnidadeMedidaDTO::new);
	}

	@Override
	protected void setValuesToEntity(UnidadeMedidaDTO dto, UnidadeMedida entity) {
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
		entity.setSigla(dto.getSigla());
		entity.setGrandezaMedida(GrandezaMedida.fromValue(dto.getGrandezaMedida()));
	}

	@Override
	protected void setValuesToDto(UnidadeMedida entity, UnidadeMedidaDTO dto) {
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		dto.setSigla(entity.getSigla());
		dto.setGrandezaMedida(entity.getGrandezaMedida().getValue());
	}
}

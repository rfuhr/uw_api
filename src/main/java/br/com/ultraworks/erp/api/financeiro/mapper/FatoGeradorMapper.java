package br.com.ultraworks.erp.api.financeiro.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.financeiro.domain.fatogerador.FatoGerador;
import br.com.ultraworks.erp.api.financeiro.domain.fatogerador.FatoGeradorDTO;
import br.com.ultraworks.erp.api.financeiro.repository.FatoGeradorRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class FatoGeradorMapper extends GenericMapper<FatoGerador, FatoGeradorDTO> {

	public FatoGeradorMapper(FatoGeradorRepository repository) {
		super(repository, FatoGerador::new, FatoGeradorDTO::new);
	}

	@Override
	protected void setValuesToEntity(FatoGeradorDTO dto, FatoGerador entity) {
		entity.setId(dto.getId());
		entity.setCodigo(dto.getCodigo());
		entity.setNome(dto.getNome());
		entity.setSigla(dto.getSigla());
	}

	@Override
	protected void setValuesToDto(FatoGerador entity, FatoGeradorDTO dto) {
		dto.setId(entity.getId());
		dto.setCodigo(entity.getCodigo());
		dto.setNome(entity.getNome());
		dto.setSigla(entity.getSigla());
	}
}

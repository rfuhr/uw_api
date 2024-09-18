package br.com.ultraworks.erp.api.financeiro.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.financeiro.domain.banco.Banco;
import br.com.ultraworks.erp.api.financeiro.domain.banco.BancoDTO;
import br.com.ultraworks.erp.api.financeiro.repository.BancoRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class BancoMapper extends GenericMapper<Banco, BancoDTO> {

	public BancoMapper(BancoRepository repository) {
		super(repository, Banco::new, BancoDTO::new);
	}

	@Override
	protected void setValuesToEntity(BancoDTO dto, Banco entity) {
		entity.setId(dto.getId());
		entity.setDv(dto.getDv());
		entity.setCodigo(dto.getCodigo());
		entity.setNome(dto.getNome());
		entity.setSigla(dto.getSigla());
	}

	@Override
	protected void setValuesToDto(Banco entity, BancoDTO dto) {
		dto.setId(entity.getId());
		dto.setDv(entity.getDv());
		dto.setCodigo(entity.getCodigo());
		dto.setNome(entity.getNome());
		dto.setSigla(entity.getSigla());
	}
}

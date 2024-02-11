package br.com.ultraworks.erp.api.tabela.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.tabela.domain.nacionalidade.Nacionalidade;
import br.com.ultraworks.erp.api.tabela.domain.nacionalidade.NacionalidadeDTO;
import br.com.ultraworks.erp.api.tabela.repository.NacionalidadeRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class NacionalidadeMapper extends GenericMapper<Nacionalidade, NacionalidadeDTO> {

	public NacionalidadeMapper(NacionalidadeRepository repository) {
		super(repository, Nacionalidade::new, NacionalidadeDTO::new);
    }

	@Override
	protected void setValuesToEntity(NacionalidadeDTO dto, Nacionalidade entity) {
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
		entity.setCodigo(dto.getCodigo());
	}

	@Override
	protected void setValuesToDto(Nacionalidade entity, NacionalidadeDTO dto) {
		dto.setId(entity.getId());
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		dto.setCodigo(entity.getCodigo());
	}
}


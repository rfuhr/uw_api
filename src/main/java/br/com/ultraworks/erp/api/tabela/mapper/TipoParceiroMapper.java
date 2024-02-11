package br.com.ultraworks.erp.api.tabela.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.tabela.domain.tipoparceiro.TipoParceiro;
import br.com.ultraworks.erp.api.tabela.domain.tipoparceiro.TipoParceiroDTO;
import br.com.ultraworks.erp.api.tabela.repository.TipoParceiroRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class TipoParceiroMapper extends GenericMapper<TipoParceiro, TipoParceiroDTO> {

	public TipoParceiroMapper(TipoParceiroRepository repository) {
		super(repository, TipoParceiro::new, TipoParceiroDTO::new);
    }

	@Override
	protected void setValuesToEntity(TipoParceiroDTO dto, TipoParceiro entity) {
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
		entity.setCodigo(dto.getCodigo());
	}

	@Override
	protected void setValuesToDto(TipoParceiro entity, TipoParceiroDTO dto) {
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		dto.setCodigo(entity.getCodigo());
	}
}


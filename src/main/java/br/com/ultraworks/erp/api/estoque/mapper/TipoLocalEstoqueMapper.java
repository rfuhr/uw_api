package br.com.ultraworks.erp.api.estoque.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.estoque.domain.tipolocalestoque.TipoLocalEstoque;
import br.com.ultraworks.erp.api.estoque.domain.tipolocalestoque.TipoLocalEstoqueDTO;
import br.com.ultraworks.erp.api.estoque.repository.TipoLocalEstoqueRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class TipoLocalEstoqueMapper extends GenericMapper<TipoLocalEstoque, TipoLocalEstoqueDTO> {

	public TipoLocalEstoqueMapper(TipoLocalEstoqueRepository repository) {
		super(repository, TipoLocalEstoque::new, TipoLocalEstoqueDTO::new);
	}

	@Override
	protected void setValuesToEntity(TipoLocalEstoqueDTO dto, TipoLocalEstoque entity) {
		entity.setId(dto.getId());
		entity.setCodigo(dto.getCodigo());
		entity.setNome(dto.getNome());
	}

	@Override
	protected void setValuesToDto(TipoLocalEstoque entity, TipoLocalEstoqueDTO dto) {
		dto.setId(entity.getId());
		dto.setCodigo(entity.getCodigo());
		dto.setNome(entity.getNome());
	}
}

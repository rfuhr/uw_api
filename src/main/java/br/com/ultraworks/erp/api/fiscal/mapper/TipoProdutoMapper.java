package br.com.ultraworks.erp.api.fiscal.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.fiscal.domain.tipoproduto.TipoProduto;
import br.com.ultraworks.erp.api.fiscal.domain.tipoproduto.TipoProdutoDTO;
import br.com.ultraworks.erp.api.fiscal.repository.TipoProdutoRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class TipoProdutoMapper extends GenericMapper<TipoProduto, TipoProdutoDTO> {

	public TipoProdutoMapper(TipoProdutoRepository repository) {
		super(repository, TipoProduto::new, TipoProdutoDTO::new);
	}

	@Override
	protected void setValuesToEntity(TipoProdutoDTO dto, TipoProduto entity) {
		entity.setId(dto.getId());
		entity.setCodigo(dto.getCodigo());
		entity.setNome(dto.getNome());
		entity.setUsoConsumo(dto.isUsoConsumo());
	}

	@Override
	protected void setValuesToDto(TipoProduto entity, TipoProdutoDTO dto) {
		dto.setId(entity.getId());
		dto.setCodigo(entity.getCodigo());
		dto.setNome(entity.getNome());
		dto.setUsoConsumo(entity.isUsoConsumo());
	}
}

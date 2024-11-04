package br.com.ultraworks.erp.api.compras.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.compras.domain.itemsimplificado.ItemSimplificado;
import br.com.ultraworks.erp.api.compras.domain.itemsimplificado.ItemSimplificadoDTO;
import br.com.ultraworks.erp.api.compras.repository.ItemSimplificadoRepository;
import br.com.ultraworks.erp.api.estoque.repository.GrupoContabilRepository;
import br.com.ultraworks.erp.api.organograma.repository.DepartamentoRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ItemSimplificadoMapper extends GenericMapper<ItemSimplificado, ItemSimplificadoDTO> {

	public ItemSimplificadoMapper(ItemSimplificadoRepository itemSimplificadoRepository,
			DepartamentoRepository departamentoRepository, GrupoContabilRepository grupoContabilRepository) {
		super(itemSimplificadoRepository, ItemSimplificado::new, ItemSimplificadoDTO::new);
	}

	@Override
	protected void setValuesToEntity(ItemSimplificadoDTO dto, ItemSimplificado entity) {
		entity.setId(dto.getId());
		entity.setCodigo(dto.getCodigo());
		entity.setNome(dto.getNome());
		entity.setDataInicioVigencia(dto.getDataInicioVigencia());
		entity.setDataFinalVigencia(dto.getDataFinalVigencia());
	}

	@Override
	protected void setValuesToDto(ItemSimplificado entity, ItemSimplificadoDTO dto) {
		dto.setId(entity.getId());
		dto.setCodigo(entity.getCodigo());
		dto.setNome(entity.getNome());
		dto.setDataInicioVigencia(entity.getDataInicioVigencia());
		dto.setDataFinalVigencia(entity.getDataFinalVigencia());
	}
}

package br.com.ultraworks.erp.api.comercial.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.comercial.domain.tabelaprecoitem.TabelaPrecoItem;
import br.com.ultraworks.erp.api.comercial.domain.tabelaprecoitem.TabelaPrecoItemDTO;
import br.com.ultraworks.erp.api.comercial.repository.TabelaPrecoItemRepository;
import br.com.ultraworks.erp.api.comercial.repository.TabelaPrecoRepository;
import br.com.ultraworks.erp.api.estoque.repository.ItemRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class TabelaPrecoItemMapper extends GenericMapper<TabelaPrecoItem, TabelaPrecoItemDTO> {

	TabelaPrecoRepository tabelaPrecoRepository;
	ItemRepository itemRepository;
	
	public TabelaPrecoItemMapper(TabelaPrecoItemRepository repository,
			TabelaPrecoRepository tabelaPrecoRepository,
			ItemRepository itemRepository) {
		super(repository, TabelaPrecoItem::new, TabelaPrecoItemDTO::new);
		this.tabelaPrecoRepository = tabelaPrecoRepository;
		this.itemRepository = itemRepository;
	}

	@Override
	protected void setValuesToEntity(TabelaPrecoItemDTO dto, TabelaPrecoItem entity) {
		entity.setId(dto.getId());
		entity.setDataInicioVigencia(dto.getDataInicioVigencia());
		entity.setDataFinalVigencia(dto.getDataFinalVigencia());
		entity.setValorCusto(dto.getValorCusto());
		entity.setValorMarkup(dto.getValorMarkup());
		entity.setValorCalculado(dto.getValorCalculado());
		entity.setValorAtual(dto.getValorAtual());
		entity.setValor(dto.getValor());
		entity.setPercentualMaximoDesconto(dto.getPercentualMaximoDesconto());
		if (dto.getTabelaPrecoId() != null) {
			entity.setTabelaPreco(tabelaPrecoRepository.findById(dto.getTabelaPrecoId())
					.orElseThrow(() -> new RegisterNotFoundException("Não encontrada Tabela de Preço com id " + dto.getTabelaPrecoId())));			
		}
		entity.setItem(itemRepository.findById(dto.getItemId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado o Item com id " + dto.getItemId())));	
	}

	@Override
	protected void setValuesToDto(TabelaPrecoItem entity, TabelaPrecoItemDTO dto) {
		dto.setId(entity.getId());
		dto.setTabelaPrecoId(entity.getTabelaPreco().getId());
		dto.setDataInicioVigencia(entity.getDataInicioVigencia());
		dto.setDataFinalVigencia(entity.getDataFinalVigencia());
		dto.setValorCusto(entity.getValorCusto());
		dto.setValorMarkup(entity.getValorMarkup());
		dto.setValorCalculado(entity.getValorCalculado());
		dto.setValorAtual(entity.getValorAtual());
		dto.setValor(entity.getValor());
		dto.setPercentualMaximoDesconto(entity.getPercentualMaximoDesconto());
		if (entity.getItem() != null) {
			dto.setItemCodigo(entity.getItem().getCodigo());
			dto.setItemNome(entity.getItem().getNome());
			dto.setItemId(entity.getItem().getId());
			if (entity.getItem().getUnidadeMedidaComercial() != null) {
				dto.setUnidadeMedidaSigla(entity.getItem().getUnidadeMedidaComercial().getSigla());				
			}
		}
	}
}
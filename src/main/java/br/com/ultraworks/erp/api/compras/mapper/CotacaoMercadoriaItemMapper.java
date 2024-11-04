package br.com.ultraworks.erp.api.compras.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.compras.domain.cotacaomercadoriaitem.CotacaoMercadoriaItem;
import br.com.ultraworks.erp.api.compras.domain.cotacaomercadoriaitem.CotacaoMercadoriaItemDTO;
import br.com.ultraworks.erp.api.compras.repository.CotacaoMercadoriaItemRepository;
import br.com.ultraworks.erp.api.compras.repository.CotacaoMercadoriaParceiroRepository;
import br.com.ultraworks.erp.api.compras.repository.ItemSimplificadoRepository;
import br.com.ultraworks.erp.api.compras.repository.SolicitacaoMercadoriaItemRepository;
import br.com.ultraworks.erp.api.estoque.repository.ItemRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class CotacaoMercadoriaItemMapper extends GenericMapper<CotacaoMercadoriaItem, CotacaoMercadoriaItemDTO> {

	private CotacaoMercadoriaParceiroRepository cotacaoMercadoriaParceiroRepository;
	private ItemRepository itemRepository;
	private ItemSimplificadoRepository itemSimplificadoRepository;
	private SolicitacaoMercadoriaItemRepository solicitacaoMercadoriaItemRepository;

	public CotacaoMercadoriaItemMapper(CotacaoMercadoriaItemRepository cotacaoMercadoriaItemRepository,
			CotacaoMercadoriaParceiroRepository cotacaoMercadoriaParceiroRepository, ItemRepository itemRepository,
			ItemSimplificadoRepository itemSimplificadoRepository,
			SolicitacaoMercadoriaItemRepository solicitacaoMercadoriaItemRepository) {
		super(cotacaoMercadoriaItemRepository, CotacaoMercadoriaItem::new, CotacaoMercadoriaItemDTO::new);
		this.cotacaoMercadoriaParceiroRepository = cotacaoMercadoriaParceiroRepository;
		this.itemRepository = itemRepository;
		this.itemSimplificadoRepository = itemSimplificadoRepository;
		this.solicitacaoMercadoriaItemRepository = solicitacaoMercadoriaItemRepository;
	}

	@Override
	protected void setValuesToEntity(CotacaoMercadoriaItemDTO dto, CotacaoMercadoriaItem entity) {
		entity.setId(dto.getId());
		if (dto.getCotacaoMercadoriaParceiroId() != null) {
			entity.setCotacaoMercadoriaParceiro(
					cotacaoMercadoriaParceiroRepository.findById(dto.getCotacaoMercadoriaParceiroId()).orElseThrow(
							() -> new RegisterNotFoundException("Não encontrado cotação de mercadoria parceiro com id "
									+ dto.getCotacaoMercadoriaParceiroId())));
		}
		if (dto.getItemId() != null) {
			entity.setItem(itemRepository.findById(dto.getItemId())
					.orElseThrow(() -> new RegisterNotFoundException("Não encontrado item com id " + dto.getItemId())));
		}
		if (dto.getItemSimplificadoId() != null) {
			entity.setItemSimplificado(itemSimplificadoRepository.findById(dto.getItemSimplificadoId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado item simplificado com id " + dto.getItemSimplificadoId())));
		}
		if (dto.getSolicitacaoMercadoriaItemId() != null) {
			entity.setSolicitacaoMercadoriaItem(
					solicitacaoMercadoriaItemRepository.findById(dto.getSolicitacaoMercadoriaItemId())
							.orElseThrow(() -> new RegisterNotFoundException(
									"Não encontrado item da solicitação de mercadoria com id "
											+ dto.getSolicitacaoMercadoriaItemId())));
		}
	}

	@Override
	protected void setValuesToDto(CotacaoMercadoriaItem entity, CotacaoMercadoriaItemDTO dto) {
		dto.setId(entity.getId());
		dto.setCotacaoMercadoriaParceiroId(entity.getCotacaoMercadoriaParceiro().getId());
		if (entity.getItem() != null) {
			dto.setItemId(entity.getItem().getId());
			dto.setItemNome(entity.getItem().getNome());
		}
		if (entity.getItemSimplificado() != null) {
			dto.setItemSimplificadoId(entity.getItemSimplificado().getId());
			dto.setItemSimplificadoNome(entity.getItemSimplificado().getNome());
		}
		if (entity.getSolicitacaoMercadoriaItem() != null) {
			dto.setSolicitacaoMercadoriaItemId(entity.getSolicitacaoMercadoriaItem().getId());
		}
	}
}
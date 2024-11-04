package br.com.ultraworks.erp.api.compras.service.cotacaomercadoria;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.compras.domain.cotacaomercadoriaitem.CotacaoMercadoriaItem;
import br.com.ultraworks.erp.api.compras.domain.cotacaomercadoriaitem.CotacaoMercadoriaItemDTO;
import br.com.ultraworks.erp.api.compras.mapper.CotacaoMercadoriaItemMapper;
import br.com.ultraworks.erp.api.compras.repository.CotacaoMercadoriaItemRepository;
import br.com.ultraworks.erp.api.compras.service.solicitacaomercadoria.SolicitacaoMercadoriaItemService;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class CotacaoMercadoriaItemService
		extends GenericService<CotacaoMercadoriaItem, Long, CotacaoMercadoriaItemDTO> {

	private SolicitacaoMercadoriaItemService solicitacaoMercadoriaItemService;

	@Autowired
	public CotacaoMercadoriaItemService(CotacaoMercadoriaItemRepository repository, CotacaoMercadoriaItemMapper mapper,
			SolicitacaoMercadoriaItemService solicitacaoMercadoriaItemService) {
		super(repository, mapper);
		this.solicitacaoMercadoriaItemService = solicitacaoMercadoriaItemService;
	}

	public List<CotacaoMercadoriaItem> getAllByCotacaoMercadoriaParceiro(Long id) {
		List<CotacaoMercadoriaItem> listRegistros = new ArrayList<>();

		listRegistros.addAll(((CotacaoMercadoriaItemRepository) repository).findByCotacaoMercadoriaParceiroId(id));
		return listRegistros;
	}

	public void criarItensCotacao(Long cotacaoMercadoriaParceiroId, List<CotacaoMercadoriaItem> itens) {
		if (itens != null) {
			itens.stream().forEach(item -> {
				repository.save(item);
			});
			solicitacaoMercadoriaItemService.cotar(itens.stream()
					.map(CotacaoMercadoriaItem::getSolicitacaoMercadoriaItem).collect(Collectors.toList()));
		}

	}
}
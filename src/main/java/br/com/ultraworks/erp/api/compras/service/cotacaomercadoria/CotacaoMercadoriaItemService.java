package br.com.ultraworks.erp.api.compras.service.cotacaomercadoria;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.compras.domain.cotacaomercadoriaitem.CotacaoMercadoriaItem;
import br.com.ultraworks.erp.api.compras.domain.cotacaomercadoriaitem.CotacaoMercadoriaItemDTO;
import br.com.ultraworks.erp.api.compras.domain.solicitacaomercadoriaitem.SolicitacaoMercadoriaItem;
import br.com.ultraworks.erp.api.compras.mapper.CotacaoMercadoriaItemMapper;
import br.com.ultraworks.erp.api.compras.repository.CotacaoMercadoriaItemRepository;
import br.com.ultraworks.erp.api.compras.repository.SolicitacaoMercadoriaItemRepository;
import br.com.ultraworks.erp.api.compras.service.solicitacaomercadoria.SolicitacaoMercadoriaItemService;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.generics.GenericService;
import br.com.ultraworks.erp.core.util.ListUtils;
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

	public void persistList(Long cotacaoMercadoriaParceiroId, List<CotacaoMercadoriaItem> itens) {
		List<CotacaoMercadoriaItem> itensSalvos = ((CotacaoMercadoriaItemRepository) repository)
				.findByCotacaoMercadoriaParceiroId(cotacaoMercadoriaParceiroId);
		
		if (itens != null) {
			itens.stream().forEach(item -> {
				repository.save(item);
			});
		}
		List<CotacaoMercadoriaItem> itExcluir = (List<CotacaoMercadoriaItem>) ListUtils
				.compararListasERetornaDiferenca(itensSalvos, itens);
		if (itExcluir != null && itExcluir.size() > 0) {
			List<SolicitacaoMercadoriaItem> itensParaLiberar = itExcluir.stream()
		            .map(CotacaoMercadoriaItem::getSolicitacaoMercadoriaItem)
		            .filter(item -> itens.stream()
		                    .map(CotacaoMercadoriaItem::getSolicitacaoMercadoriaItem)
		                    .noneMatch(cotado -> cotado.getId().equals(item.getId()))
		            )
		            .collect(Collectors.toList());
			
			solicitacaoMercadoriaItemService.liberar(itensParaLiberar);
			itExcluir.stream().forEach(it -> repository.deleteById(it.getId()));
		}
	}

	@Override
	public void delete(List<Long> ids) {
		List<CotacaoMercadoriaItem> itens = ids.stream()
			    .map(id -> repository.findById(id).orElseThrow(RegisterNotFoundException::new))
			    .collect(Collectors.toList());
		solicitacaoMercadoriaItemService.liberar(itens.stream()
				.map(CotacaoMercadoriaItem::getSolicitacaoMercadoriaItem).collect(Collectors.toList()));
		repository.deleteAllById(ids);
	}}
package br.com.ultraworks.erp.api.compras.service.cotacaomercadoria;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.compras.domain.cotacaomercadoriaitem.CotacaoMercadoriaItem;
import br.com.ultraworks.erp.api.compras.domain.cotacaomercadoriaparceiro.CotacaoMercadoriaParceiro;
import br.com.ultraworks.erp.api.compras.domain.cotacaomercadoriaparceiro.CotacaoMercadoriaParceiroDTO;
import br.com.ultraworks.erp.api.compras.mapper.CotacaoMercadoriaParceiroMapper;
import br.com.ultraworks.erp.api.compras.repository.CotacaoMercadoriaParceiroRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import br.com.ultraworks.erp.core.security.domain.CustomUser;
import br.com.ultraworks.erp.core.util.ListUtils;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class CotacaoMercadoriaParceiroService
		extends GenericService<CotacaoMercadoriaParceiro, Long, CotacaoMercadoriaParceiroDTO> {

	CotacaoMercadoriaItemService cotacaoMercadoriaItemService;

	@Autowired
	public CotacaoMercadoriaParceiroService(CotacaoMercadoriaParceiroRepository repository,
			CotacaoMercadoriaParceiroMapper mapper, CotacaoMercadoriaItemService cotacaoMercadoriaItemService) {
		super(repository, mapper);
		this.cotacaoMercadoriaItemService = cotacaoMercadoriaItemService;
	}

	public List<CotacaoMercadoriaParceiro> getAllByCotacaoMercadoria(Long id) {
		List<CotacaoMercadoriaParceiro> listRegistros = new ArrayList<>();

		List<CotacaoMercadoriaParceiro> parceiros = ((CotacaoMercadoriaParceiroRepository) repository)
				.findByCotacaoMercadoriaId(id);
		parceiros.forEach(parc -> {
			parc.setItens(new ArrayList<>());
			parc.getItens().addAll(cotacaoMercadoriaItemService.getAllByCotacaoMercadoriaParceiro(parc.getId()));
		});
		listRegistros.addAll(parceiros);
		return listRegistros;
	}

	public void criarCotacaoParceiros(Long cotacaoMercadoriaId, List<CotacaoMercadoriaParceiro> parceiros) {
		if (parceiros != null)
			parceiros.stream().forEach(parceiro -> {
				List<CotacaoMercadoriaItem> itensSalvar = parceiro.getItens();
				repository.save(parceiro);
				itensSalvar.forEach(item -> {
					item.setCotacaoMercadoriaParceiro(parceiro);
				});
				cotacaoMercadoriaItemService.criarItensCotacao(parceiro.getId(), itensSalvar);
			});
	}
}
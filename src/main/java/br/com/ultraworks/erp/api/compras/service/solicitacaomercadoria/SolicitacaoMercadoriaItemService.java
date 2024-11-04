package br.com.ultraworks.erp.api.compras.service.solicitacaomercadoria;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.compras.domain.situacaosolicitacaomercadoria.SituacaoSolicitacaoMercadoria;
import br.com.ultraworks.erp.api.compras.domain.solicitacaomercadoria.SolicitacaoMercadoria;
import br.com.ultraworks.erp.api.compras.domain.solicitacaomercadoriaitem.SolicitacaoMercadoriaItem;
import br.com.ultraworks.erp.api.compras.domain.solicitacaomercadoriaitem.SolicitacaoMercadoriaItemDTO;
import br.com.ultraworks.erp.api.compras.domain.statussolicitacaomercadoriaitem.StatusSolicitacaoMercadoriaItem;
import br.com.ultraworks.erp.api.compras.event.SolicitacaoMercadoriaItemSituacaoChangedEvent;
import br.com.ultraworks.erp.api.compras.mapper.SolicitacaoMercadoriaItemMapper;
import br.com.ultraworks.erp.api.compras.repository.SolicitacaoMercadoriaItemRepository;
import br.com.ultraworks.erp.api.seguranca.domain.usuario.Usuario;
import br.com.ultraworks.erp.api.seguranca.service.UsuarioService;
import br.com.ultraworks.erp.core.events.EventPublisher;
import br.com.ultraworks.erp.core.generics.GenericService;
import br.com.ultraworks.erp.core.security.domain.CustomUser;

@Service
public class SolicitacaoMercadoriaItemService
		extends GenericService<SolicitacaoMercadoriaItem, Long, SolicitacaoMercadoriaItemDTO> {

	private UsuarioService usuarioService;
	private final EventPublisher eventPublisher;

	@Autowired
	public SolicitacaoMercadoriaItemService(SolicitacaoMercadoriaItemRepository repository,
			SolicitacaoMercadoriaItemMapper mapper, UsuarioService usuarioService, EventPublisher eventPublisher) {
		super(repository, mapper);
		this.usuarioService = usuarioService;
		this.eventPublisher = eventPublisher;
	}

	public List<SolicitacaoMercadoriaItem> getAllBySolicitacaoMercadoria(Long id) {
		List<SolicitacaoMercadoriaItem> listRegistros = new ArrayList<>();

		listRegistros.addAll(((SolicitacaoMercadoriaItemRepository) repository).findBySolicitacaoMercadoriaId(id));
		return listRegistros;
	}

	@Override
	public SolicitacaoMercadoriaItem save(SolicitacaoMercadoriaItem entity) {
		throw new RuntimeException("Not used method.");
	}

	public void criarItens(Long solicitacaoMercadoriaId, List<SolicitacaoMercadoriaItem> solicitacoes) {
		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<Usuario> usuarioOptional = usuarioService.findByUserId(user.getId());
		if (solicitacoes != null)
			solicitacoes.stream().forEach(solicitacao -> {
				solicitacao.setUsuarioSolicitacao(usuarioOptional.get());
				repository.save(solicitacao);
			});
	}

	public void autorizar(List<SolicitacaoMercadoriaItem> itens) {
		itens.forEach(item -> item.setStatus(StatusSolicitacaoMercadoriaItem.AUTORIZADA));
		repository.saveAll(itens);
	}

	public void negar(List<SolicitacaoMercadoriaItem> itens) {
		itens.forEach(item -> item.setStatus(StatusSolicitacaoMercadoriaItem.NAO_AUTORIZADA));
		repository.saveAll(itens);
	}

	public void cotar(List<SolicitacaoMercadoriaItem> itens) {
		itens.forEach(item -> item.setStatus(StatusSolicitacaoMercadoriaItem.COTADA));
		repository.saveAll(itens);
		eventPublisher.publishEvent(new SolicitacaoMercadoriaItemSituacaoChangedEvent(
				itens.stream().map(SolicitacaoMercadoriaItem::getSolicitacaoMercadoria)
						.map(SolicitacaoMercadoria::getId).collect(Collectors.toList())));

	}

}
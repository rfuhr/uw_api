package br.com.ultraworks.erp.api.compras.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.compras.domain.solicitacaomercadoriaitem.SolicitacaoMercadoriaItem;
import br.com.ultraworks.erp.api.compras.domain.solicitacaomercadoriaitem.SolicitacaoMercadoriaItemDTO;
import br.com.ultraworks.erp.api.compras.domain.statussolicitacaomercadoriaitem.StatusSolicitacaoMercadoriaItem;
import br.com.ultraworks.erp.api.compras.domain.urgenciasolicitacaomercadoria.UrgenciaSolicitacaoMercadoria;
import br.com.ultraworks.erp.api.compras.repository.ItemSimplificadoRepository;
import br.com.ultraworks.erp.api.compras.repository.SolicitacaoMercadoriaItemRepository;
import br.com.ultraworks.erp.api.compras.repository.SolicitacaoMercadoriaRepository;
import br.com.ultraworks.erp.api.estoque.repository.ItemRepository;
import br.com.ultraworks.erp.api.organograma.repository.DepartamentoRepository;
import br.com.ultraworks.erp.api.seguranca.repository.UsuarioRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class SolicitacaoMercadoriaItemMapper
		extends GenericMapper<SolicitacaoMercadoriaItem, SolicitacaoMercadoriaItemDTO> {

	private SolicitacaoMercadoriaRepository solicitacaoMercadoriaRepository;
	private ItemRepository itemRepository;
	private ItemSimplificadoRepository itemSimplificadoRepository;
	private DepartamentoRepository departamentoRepository;
	private UsuarioRepository usuarioRepository;

	public SolicitacaoMercadoriaItemMapper(SolicitacaoMercadoriaItemRepository solicitacaoMercadoriaItemRepository,
			SolicitacaoMercadoriaRepository solicitacaoMercadoriaRepository, ItemRepository itemRepository,
			ItemSimplificadoRepository itemSimplificadoRepository, DepartamentoRepository departamentoRepository,
			UsuarioRepository usuarioRepository) {
		super(solicitacaoMercadoriaItemRepository, SolicitacaoMercadoriaItem::new, SolicitacaoMercadoriaItemDTO::new);
		this.solicitacaoMercadoriaRepository = solicitacaoMercadoriaRepository;
		this.itemRepository = itemRepository;
		this.itemSimplificadoRepository = itemSimplificadoRepository;
		this.departamentoRepository = departamentoRepository;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	protected void setValuesToEntity(SolicitacaoMercadoriaItemDTO dto, SolicitacaoMercadoriaItem entity) {
		entity.setId(dto.getId());
		if (dto.getSolicitacaoMercadoriaId() != null) {
			entity.setSolicitacaoMercadoria(solicitacaoMercadoriaRepository.findById(dto.getSolicitacaoMercadoriaId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado solicitação de mercadoria com id " + dto.getSolicitacaoMercadoriaId())));
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
		if (dto.getDepartamentoEntregaId() != null) {
			entity.setDepartamentoEntrega(departamentoRepository.findById(dto.getDepartamentoEntregaId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado departamento com id " + dto.getDepartamentoEntregaId())));
		}
		entity.setQuantidadeSolicitada(dto.getQuantidadeSolicitada());
		entity.setQuantidadeEnviada(dto.getQuantidadeEnviada());
		entity.setQuantidadeCancelada(dto.getQuantidadeCancelada());
		entity.setObservacao(dto.getObservacao());
		if (dto.getUsuarioSolicitacaoId() != null) {
			entity.setUsuarioSolicitacao(usuarioRepository.findById(dto.getUsuarioSolicitacaoId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado usuário com id " + dto.getUsuarioSolicitacaoId())));
		}
		entity.setDataSolicitacao(dto.getDataSolicitacao());
		if (dto.getUsuarioAtendenteId() != null) {
			entity.setUsuarioAtendente(usuarioRepository.findById(dto.getUsuarioAtendenteId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado usuário com id " + dto.getUsuarioAtendenteId())));
		}
		entity.setDataAtendente(dto.getDataAtendente());
		entity.setPrevisaoDiasUtilizacao(dto.getPrevisaoDiasUtilizacao());
		entity.setUrgenciaSolicitacaoMercadoria(
				UrgenciaSolicitacaoMercadoria.fromValue(dto.getUrgenciaSolicitacaoMercadoria()));
		entity.setStatus(StatusSolicitacaoMercadoriaItem.fromValue(dto.getStatus()));
	}

	@Override
	protected void setValuesToDto(SolicitacaoMercadoriaItem entity, SolicitacaoMercadoriaItemDTO dto) {
		dto.setId(entity.getId());
		dto.setSolicitacaoMercadoriaId(entity.getSolicitacaoMercadoria().getId());
		if (entity.getItem() != null) {
			dto.setItemId(entity.getItem().getId());
			dto.setItemNome(entity.getItem().getNome());
		}
		if (entity.getItemSimplificado() != null) {
			dto.setItemSimplificadoId(entity.getItemSimplificado().getId());
			dto.setItemSimplificadoNome(entity.getItemSimplificado().getNome());
		}
		dto.setDepartamentoEntregaId(entity.getDepartamentoEntrega().getId());
		dto.setDepartamentoEntregaNome(entity.getDepartamentoEntrega().getNome());
		dto.setQuantidadeSolicitada(entity.getQuantidadeSolicitada());
		dto.setQuantidadeEnviada(entity.getQuantidadeEnviada());
		dto.setQuantidadeCancelada(entity.getQuantidadeCancelada());
		dto.setObservacao(entity.getObservacao());
		dto.setUsuarioSolicitacaoId(entity.getUsuarioSolicitacao().getId());
		dto.setUsuarioSolicitacaoNome(entity.getUsuarioSolicitacao().getNome());
		dto.setDataSolicitacao(entity.getDataSolicitacao());
		if (entity.getUsuarioAtendente() != null) {
			dto.setUsuarioAtendenteId(entity.getUsuarioAtendente().getId());
			dto.setUsuarioAtendenteNome(entity.getUsuarioAtendente().getNome());
		}
		dto.setDataAtendente(entity.getDataAtendente());
		dto.setPrevisaoDiasUtilizacao(entity.getPrevisaoDiasUtilizacao());
		dto.setUrgenciaSolicitacaoMercadoria(entity.getUrgenciaSolicitacaoMercadoria().getValue());
		dto.setUrgenciaSolicitacaoMercadoriaNome(entity.getUrgenciaSolicitacaoMercadoria().getName());
		dto.setStatus(entity.getStatus().getValue());
		dto.setStatusNome(entity.getStatus().getName());
	}
}

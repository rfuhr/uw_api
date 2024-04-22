package br.com.ultraworks.erp.api.estoque.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.estoque.domain.item.Item;
import br.com.ultraworks.erp.api.estoque.domain.item.ItemDTO;
import br.com.ultraworks.erp.api.estoque.repository.ItemRepository;
import br.com.ultraworks.erp.api.estoque.service.LinhaService;
import br.com.ultraworks.erp.api.estoque.service.MarcaService;
import br.com.ultraworks.erp.api.estoque.service.PlanoClassificacaoItemService;
import br.com.ultraworks.erp.api.fiscal.service.ClassificacaoOperacaoService;
import br.com.ultraworks.erp.api.fiscal.service.GrupoTributacaoService;
import br.com.ultraworks.erp.api.fiscal.service.NcmService;
import br.com.ultraworks.erp.api.fiscal.service.OrigemService;
import br.com.ultraworks.erp.api.tabela.service.UnidadeMedidaService;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ItemMapper extends GenericMapper<Item, ItemDTO> {

	private UnidadeMedidaService unidadeMedidaService;
	private MarcaService marcaService;
	private LinhaService linhaService;
	private PlanoClassificacaoItemService planoClassificacaoItemService;
	private OrigemService origemService;
	private NcmService ncmService;
	private GrupoTributacaoService grupoTributacaoService;
	private ClassificacaoOperacaoService classificacaoOperacaoService;

	public ItemMapper(ItemRepository repository, UnidadeMedidaService unidadeMedidaService, MarcaService marcaService,
			LinhaService linhaService, PlanoClassificacaoItemService planoClassificacaoItemService,
			OrigemService origemService, NcmService ncmService, GrupoTributacaoService grupoTributacaoService,
			ClassificacaoOperacaoService classificacaoOperacaoService) {
		super(repository, Item::new, ItemDTO::new);
		this.unidadeMedidaService = unidadeMedidaService;
		this.marcaService = marcaService;
		this.linhaService = linhaService;
		this.planoClassificacaoItemService = planoClassificacaoItemService;
		this.origemService = origemService;
		this.ncmService = ncmService;
		this.grupoTributacaoService = grupoTributacaoService;
		this.classificacaoOperacaoService = classificacaoOperacaoService;
	}

	@Override
	protected void setValuesToEntity(ItemDTO dto, Item entity) {
		entity.setId(dto.getId());
		entity.setCodigo(dto.getCodigo());
		entity.setNome(dto.getNome());
		entity.setSku(dto.getSku());
		entity.setDescritivo(dto.getDescritivo());
		entity.setUnidadeMedidaComercial(unidadeMedidaService.getById(dto.getUnidadeMedidaComercialId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado unidade de medida comercial com id " + dto.getUnidadeMedidaComercialId())));
		if (dto.getMarcaId() != null && dto.getMarcaId() > 0)
			entity.setMarca(marcaService.getById(dto.getMarcaId()).orElseThrow(
					() -> new RegisterNotFoundException("Não encontrado marca com id " + dto.getMarcaId())));
		if (dto.getLinhaId() != null && dto.getLinhaId() > 0)
			entity.setLinha(linhaService.getById(dto.getLinhaId()).orElseThrow(
					() -> new RegisterNotFoundException("Não encontrado linha com id " + dto.getLinhaId())));
		if (dto.getPlanoCassificacaoItemId() != null && dto.getPlanoCassificacaoItemId() > 0)
			entity.setPlanoClassificacaoItem(planoClassificacaoItemService.getById(dto.getPlanoCassificacaoItemId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado plano de classificação com id " + dto.getPlanoCassificacaoItemId())));
		entity.setGtinEan(dto.getGtinEan());
		entity.setProdutoProprio(dto.isProdutoProprio());
		entity.setFracionado(dto.isFracionado());
		entity.setControlaEstoque(dto.isControlaEstoque());
		if (dto.getUnidadeMedidaEstoqueId() != null && dto.getUnidadeMedidaEstoqueId() > 0)
			entity.setUnidadeMedidaEstoque(unidadeMedidaService.getById(dto.getUnidadeMedidaEstoqueId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado unidade de medida estoque com id " + dto.getUnidadeMedidaEstoqueId())));
		entity.setQuantidadeMinimaEstoque(dto.getQuantidadeMinimaEstoque());
		entity.setQuantidadeMaximaEstoque(dto.getQuantidadeMaximaEstoque());
		entity.setQuantidadeIdealEstoque(dto.getQuantidadeIdealEstoque());
		entity.setQuantidadeAlertaEstoque(dto.getQuantidadeAlertaEstoque());

		entity.setOrigem(origemService.getById(dto.getOrigemId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado origem com id " + dto.getOrigemId())));
		entity.setNcm(ncmService.getById(dto.getNcmId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado ncm com id " + dto.getNcmId())));
		entity.setUnidadeMedidaTributavel(unidadeMedidaService.getById(dto.getUnidadeMedidaTributavelId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado unidade de medida tributável com id " + dto.getUnidadeMedidaTributavelId())));
		if (dto.getGrupoTributacaoId() != null && dto.getGrupoTributacaoId() > 0)
			entity.setGrupoTributacao(grupoTributacaoService.getById(dto.getGrupoTributacaoId()).orElseThrow(
					() -> new RegisterNotFoundException("Não encontrado Grupo Tributação com id " + dto.getGrupoTributacaoId())));
		if (dto.getClassificacaoOperacaoId() != null && dto.getClassificacaoOperacaoId() > 0)
			entity.setClassificacaoOperacao(classificacaoOperacaoService.getById(dto.getClassificacaoOperacaoId()).orElseThrow(
					() -> new RegisterNotFoundException("Não encontrado Classificação Operação com id " + dto.getClassificacaoOperacaoId())));		
	}

	@Override
	protected void setValuesToDto(Item entity, ItemDTO dto) {
		dto.setId(entity.getId());
		dto.setCodigo(entity.getCodigo());
		dto.setNome(entity.getNome());
		dto.setSku(entity.getSku());
		dto.setDescritivo(entity.getDescritivo());
		dto.setUnidadeMedidaComercialId(entity.getUnidadeMedidaComercial().getId());
		dto.setUnidadeMedidaComercialNome(entity.getUnidadeMedidaComercial().getNome());

		if (entity.getMarca() != null)
			dto.setMarcaId(entity.getMarca().getId());
		if (entity.getLinha() != null)
			dto.setLinhaId(entity.getLinha().getId());
		if (entity.getPlanoClassificacaoItem() != null)
			dto.setPlanoCassificacaoItemId(entity.getPlanoClassificacaoItem().getId());
		dto.setGtinEan(entity.getGtinEan());
		dto.setFracionado(entity.isFracionado());
		dto.setControlaEstoque(entity.isControlaEstoque());
		if (entity.getUnidadeMedidaEstoque() != null)
			dto.setUnidadeMedidaEstoqueId(entity.getUnidadeMedidaEstoque().getId());
		dto.setQuantidadeMinimaEstoque(entity.getQuantidadeMinimaEstoque());
		dto.setQuantidadeMaximaEstoque(entity.getQuantidadeMaximaEstoque());
		dto.setQuantidadeIdealEstoque(entity.getQuantidadeIdealEstoque());
		dto.setQuantidadeAlertaEstoque(entity.getQuantidadeAlertaEstoque());

		dto.setOrigemId(entity.getOrigem().getId());
		dto.setOrigemNome(entity.getOrigem().getNome());
		dto.setNcmId(entity.getNcm().getId());
		dto.setNcmCodigo(entity.getNcm().getCodigo());
		dto.setUnidadeMedidaTributavelId(entity.getUnidadeMedidaTributavel().getId());
		dto.setProdutoProprio(entity.isProdutoProprio());
		if (entity.getGrupoTributacao() != null) {
			dto.setGrupoTributacaoId(entity.getGrupoTributacao().getId());
			dto.setGrupoTributacaoNome(entity.getGrupoTributacao().getNome());
		}
		if (entity.getClassificacaoOperacao() != null) {
			dto.setClassificacaoOperacaoId(entity.getClassificacaoOperacao().getId());
			dto.setClassificacaoOperacaoNome(entity.getClassificacaoOperacao().getNome());
		}
	}
}

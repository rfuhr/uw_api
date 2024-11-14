package br.com.ultraworks.erp.api.compras.mapper;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.compras.domain.cotacaomercadoriaparceiro.CotacaoMercadoriaParceiro;
import br.com.ultraworks.erp.api.compras.domain.cotacaomercadoriaparceiro.CotacaoMercadoriaParceiroDTO;
import br.com.ultraworks.erp.api.compras.domain.situacaocotacaomercadoriaparceiro.SituacaoCotacaoMercadoriaParceiro;
import br.com.ultraworks.erp.api.compras.repository.CotacaoMercadoriaParceiroRepository;
import br.com.ultraworks.erp.api.compras.repository.CotacaoMercadoriaRepository;
import br.com.ultraworks.erp.api.relacionamento.mapper.ParceiroLocalMapper;
import br.com.ultraworks.erp.api.relacionamento.service.ParceiroLocalService;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class CotacaoMercadoriaParceiroMapper
		extends GenericMapper<CotacaoMercadoriaParceiro, CotacaoMercadoriaParceiroDTO> {

	private CotacaoMercadoriaRepository cotacaoMercadoriaRepository;
	private ParceiroLocalService parceiroLocalService;
	private CotacaoMercadoriaItemMapper cotacaoMercadoriaItemMapper;
	private ParceiroLocalMapper parceiroLocalMapper;

	public CotacaoMercadoriaParceiroMapper(CotacaoMercadoriaParceiroRepository cotacaoMercadoriaParceiroRepository,
			CotacaoMercadoriaRepository cotacaoMercadoriaRepository, ParceiroLocalService parceiroLocalService,
			CotacaoMercadoriaItemMapper cotacaoMercadoriaItemMapper, ParceiroLocalMapper parceiroLocalMapper) {
		super(cotacaoMercadoriaParceiroRepository, CotacaoMercadoriaParceiro::new, CotacaoMercadoriaParceiroDTO::new);
		this.cotacaoMercadoriaRepository = cotacaoMercadoriaRepository;
		this.parceiroLocalService = parceiroLocalService;
		this.cotacaoMercadoriaItemMapper = cotacaoMercadoriaItemMapper;
		this.parceiroLocalMapper = parceiroLocalMapper;
	}

	@Override
	protected void setValuesToEntity(CotacaoMercadoriaParceiroDTO dto, CotacaoMercadoriaParceiro entity) {
		entity.setId(dto.getId());
		if (dto.getCotacaoMercadoriaId() != null) {
			entity.setCotacaoMercadoria(cotacaoMercadoriaRepository.findById(dto.getCotacaoMercadoriaId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado cotação de mercadoria com id " + dto.getCotacaoMercadoriaId())));
		}
		if (dto.getParceiroLocalId() != null) {
			entity.setParceiroLocal(parceiroLocalService.getById(dto.getParceiroLocalId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado local do parceiro com id " + dto.getParceiroLocalId())));
		}
		if (dto.getItens() != null && dto.getItens().size() > 0) {
			entity.setItens(new ArrayList<>());
			entity.getItens().addAll(cotacaoMercadoriaItemMapper.toEntity(dto.getItens()));
		}
		if (dto.getSituacao() != null) {
			entity.setSituacao(SituacaoCotacaoMercadoriaParceiro.fromValue(dto.getSituacao()));
		}
	}

	@Override
	protected void setValuesToDto(CotacaoMercadoriaParceiro entity, CotacaoMercadoriaParceiroDTO dto) {
		dto.setId(entity.getId());
		dto.setCotacaoMercadoriaId(entity.getCotacaoMercadoria().getId());
		if (entity.getParceiroLocal() != null) {
			dto.setParceiroLocalId(entity.getParceiroLocal().getId());
			dto.setParceiroLocal(
					parceiroLocalMapper.toDto(parceiroLocalService.getById(entity.getParceiroLocal().getId()).get()));
		}
		if (entity.getSituacao() != null) {
			dto.setSituacao(entity.getSituacao().getValue());
			dto.setSituacaoNome(entity.getSituacao().getName());
		}
		dto.setItens(new ArrayList<>());
		if (entity.getItens() != null) {
			dto.getItens().addAll(cotacaoMercadoriaItemMapper.toDto(entity.getItens()));
		}
	}
}

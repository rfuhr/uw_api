package br.com.ultraworks.erp.api.compras.mapper;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.compras.domain.cotacaomercadoriaparceiro.CotacaoMercadoriaParceiro;
import br.com.ultraworks.erp.api.compras.domain.cotacaomercadoriaparceiro.CotacaoMercadoriaParceiroDTO;
import br.com.ultraworks.erp.api.compras.repository.CotacaoMercadoriaParceiroRepository;
import br.com.ultraworks.erp.api.compras.repository.CotacaoMercadoriaRepository;
import br.com.ultraworks.erp.api.relacionamento.repository.ParceiroLocalRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class CotacaoMercadoriaParceiroMapper
		extends GenericMapper<CotacaoMercadoriaParceiro, CotacaoMercadoriaParceiroDTO> {

	private CotacaoMercadoriaRepository cotacaoMercadoriaRepository;
	private ParceiroLocalRepository parceiroLocalRepository;
	private CotacaoMercadoriaItemMapper cotacaoMercadoriaItemMapper;

	public CotacaoMercadoriaParceiroMapper(CotacaoMercadoriaParceiroRepository cotacaoMercadoriaParceiroRepository,
			CotacaoMercadoriaRepository cotacaoMercadoriaRepository, ParceiroLocalRepository parceiroLocalRepository,
			CotacaoMercadoriaItemMapper cotacaoMercadoriaItemMapper) {
		super(cotacaoMercadoriaParceiroRepository, CotacaoMercadoriaParceiro::new, CotacaoMercadoriaParceiroDTO::new);
		this.cotacaoMercadoriaRepository = cotacaoMercadoriaRepository;
		this.parceiroLocalRepository = parceiroLocalRepository;
		this.cotacaoMercadoriaItemMapper = cotacaoMercadoriaItemMapper;
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
			entity.setParceiroLocal(parceiroLocalRepository.findById(dto.getParceiroLocalId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado local do parceiro com id " + dto.getParceiroLocalId())));
		}
		if (dto.getItens() != null && dto.getItens().size() > 0) {
			entity.setItens(new ArrayList<>());
			entity.getItens().addAll(cotacaoMercadoriaItemMapper.toEntity(dto.getItens()));
		}
	}

	@Override
	protected void setValuesToDto(CotacaoMercadoriaParceiro entity, CotacaoMercadoriaParceiroDTO dto) {
		dto.setId(entity.getId());
		dto.setCotacaoMercadoriaId(entity.getCotacaoMercadoria().getId());
		if (entity.getParceiroLocal() != null) {
			dto.setParceiroLocalId(entity.getParceiroLocal().getId());
			dto.setParceiroLocalNome(entity.getParceiroLocal().getNomeLocal());
			dto.setParceiroId(entity.getParceiroLocal().getParceiro().getId());
			dto.setParceiroNomeRazaoSocial(entity.getParceiroLocal().getParceiro().getNomeRazaoSocial());
		}
		dto.setItens(new ArrayList<>());
		if (entity.getItens() != null) {
			dto.getItens().addAll(cotacaoMercadoriaItemMapper.toDto(entity.getItens()));
		}
	}
}

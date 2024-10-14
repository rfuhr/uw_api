package br.com.ultraworks.erp.api.agricola.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.agricola.domain.romaneioagricolaparcelafixacao.RomaneioAgricolaParcelaFixacao;
import br.com.ultraworks.erp.api.agricola.domain.romaneioagricolaparcelafixacao.RomaneioAgricolaParcelaFixacaoDTO;
import br.com.ultraworks.erp.api.agricola.repository.RomaneioAgricolaParcelaFixacaoRepository;
import br.com.ultraworks.erp.api.agricola.repository.RomaneioAgricolaRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class RomaneioAgricolaParcelaFixacaoMapper
		extends GenericMapper<RomaneioAgricolaParcelaFixacao, RomaneioAgricolaParcelaFixacaoDTO> {

	private RomaneioAgricolaRepository romaneioAgricolaRepository;

	public RomaneioAgricolaParcelaFixacaoMapper(
			RomaneioAgricolaParcelaFixacaoRepository romaneioAgricolaNotaRepository,
			RomaneioAgricolaRepository romaneioAgricolaRepository) {
		super(romaneioAgricolaNotaRepository, RomaneioAgricolaParcelaFixacao::new, RomaneioAgricolaParcelaFixacaoDTO::new);
		this.romaneioAgricolaRepository = romaneioAgricolaRepository;
	}

	@Override
	protected void setValuesToEntity(RomaneioAgricolaParcelaFixacaoDTO dto, RomaneioAgricolaParcelaFixacao entity) {
		entity.setId(dto.getId());
		if (dto.getRomaneioAgricolaId() != null) {
			entity.setRomaneioAgricola(romaneioAgricolaRepository.findById(dto.getRomaneioAgricolaId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado romaneio agrícola com id " + dto.getRomaneioAgricolaId())));
		}
		entity.setDataVencimento(dto.getDataVencimento());
		entity.setValorParcela(dto.getValorParcela());
	}

	@Override
	protected void setValuesToDto(RomaneioAgricolaParcelaFixacao entity, RomaneioAgricolaParcelaFixacaoDTO dto) {
		dto.setId(entity.getId());
		dto.setRomaneioAgricolaId(entity.getRomaneioAgricola().getId());
		dto.setDataVencimento(entity.getDataVencimento());
		dto.setValorParcela(entity.getValorParcela());
	}
}

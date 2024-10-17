package br.com.ultraworks.erp.api.agricola.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.agricola.domain.contratoagricolaparcela.ContratoAgricolaParcela;
import br.com.ultraworks.erp.api.agricola.domain.contratoagricolaparcela.ContratoAgricolaParcelaDTO;
import br.com.ultraworks.erp.api.agricola.repository.ContratoAgricolaParcelaRepository;
import br.com.ultraworks.erp.api.agricola.repository.ContratoAgricolaRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ContratoAgricolaParcelaMapper extends GenericMapper<ContratoAgricolaParcela, ContratoAgricolaParcelaDTO> {

	private ContratoAgricolaRepository contratoAgricolaRepository;

	public ContratoAgricolaParcelaMapper(ContratoAgricolaParcelaRepository contratoAgricolaParcelaRepository,
			ContratoAgricolaRepository contratoAgricolaRepository) {
		super(contratoAgricolaParcelaRepository, ContratoAgricolaParcela::new, ContratoAgricolaParcelaDTO::new);
		this.contratoAgricolaRepository = contratoAgricolaRepository;
	}

	@Override
	protected void setValuesToEntity(ContratoAgricolaParcelaDTO dto, ContratoAgricolaParcela entity) {
		entity.setId(dto.getId());
		if (dto.getContratoAgricolaId() != null) {
			entity.setContratoAgricola(contratoAgricolaRepository.findById(dto.getContratoAgricolaId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado romaneio agrícola com id " + dto.getContratoAgricolaId())));
		}
		entity.setNumeroParcela(dto.getNumeroParcela());
		entity.setDataVencimento(dto.getDataVencimento());
		entity.setValorParcela(dto.getValorParcela());
	}

	@Override
	protected void setValuesToDto(ContratoAgricolaParcela entity, ContratoAgricolaParcelaDTO dto) {
		dto.setId(entity.getId());
		dto.setContratoAgricolaId(entity.getContratoAgricola().getId());
		dto.setNumeroParcela(entity.getNumeroParcela());
		dto.setDataVencimento(entity.getDataVencimento());
		dto.setValorParcela(entity.getValorParcela());
	}
}

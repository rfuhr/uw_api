package br.com.ultraworks.erp.api.agricola.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.agricola.domain.contratoagricoladesconto.ContratoAgricolaDesconto;
import br.com.ultraworks.erp.api.agricola.domain.contratoagricoladesconto.ContratoAgricolaDescontoDTO;
import br.com.ultraworks.erp.api.agricola.repository.ContratoAgricolaDescontoRepository;
import br.com.ultraworks.erp.api.agricola.repository.ContratoAgricolaRepository;
import br.com.ultraworks.erp.api.agricola.repository.TipoCalculoAgricolaRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ContratoAgricolaDescontoMapper
		extends GenericMapper<ContratoAgricolaDesconto, ContratoAgricolaDescontoDTO> {

	private ContratoAgricolaRepository contratoAgricolaRepository;
	private TipoCalculoAgricolaRepository tipoCalculoAgricolaRepository;

	public ContratoAgricolaDescontoMapper(ContratoAgricolaDescontoRepository contratoAgricolaDescontoRepository,
			ContratoAgricolaRepository contratoAgricolaRepository,
			TipoCalculoAgricolaRepository tipoCalculoAgricolaRepository) {
		super(contratoAgricolaDescontoRepository, ContratoAgricolaDesconto::new, ContratoAgricolaDescontoDTO::new);
		this.contratoAgricolaRepository = contratoAgricolaRepository;
		this.tipoCalculoAgricolaRepository = tipoCalculoAgricolaRepository;
	}

	@Override
	protected void setValuesToEntity(ContratoAgricolaDescontoDTO dto, ContratoAgricolaDesconto entity) {
		entity.setId(dto.getId());
		if (dto.getContratoAgricolaId() != null) {
			entity.setContratoAgricola(contratoAgricolaRepository.findById(dto.getContratoAgricolaId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado romaneio agrícola com id " + dto.getContratoAgricolaId())));
		}
		if (dto.getTipoCalculoAgricolaId() != null) {
			entity.setTipoCalculoAgricola(tipoCalculoAgricolaRepository.findById(dto.getTipoCalculoAgricolaId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado tipo de cálculo agrícola com id " + dto.getTipoCalculoAgricolaId())));
		}
		entity.setPercentualTaxaContrato(dto.getPercentualTaxaContrato());
		entity.setPercentualTaxaAtual(dto.getPercentualTaxaAtual());
		entity.setValor(dto.getValor());
	}

	@Override
	protected void setValuesToDto(ContratoAgricolaDesconto entity, ContratoAgricolaDescontoDTO dto) {
		dto.setId(entity.getId());
		dto.setContratoAgricolaId(entity.getContratoAgricola().getId());
		if (entity.getTipoCalculoAgricola() != null) {
			dto.setTipoCalculoAgricolaId(entity.getTipoCalculoAgricola().getId());
			dto.setTipoCalculoAgricolaNome(entity.getTipoCalculoAgricola().getNome());
		}
		dto.setPercentualTaxaContrato(entity.getPercentualTaxaContrato());
		dto.setPercentualTaxaAtual(entity.getPercentualTaxaAtual());
		dto.setValor(entity.getValor());
	}
}

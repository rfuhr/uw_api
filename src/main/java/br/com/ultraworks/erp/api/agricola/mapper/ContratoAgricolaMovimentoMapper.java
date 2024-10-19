package br.com.ultraworks.erp.api.agricola.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.agricola.domain.contratoagricolamovimento.ContratoAgricolaMovimento;
import br.com.ultraworks.erp.api.agricola.domain.contratoagricolamovimento.ContratoAgricolaMovimentoDTO;
import br.com.ultraworks.erp.api.agricola.repository.ContratoAgricolaMovimentoRepository;
import br.com.ultraworks.erp.api.agricola.repository.ContratoAgricolaRepository;
import br.com.ultraworks.erp.api.tabela.repository.OperacaoInternaRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ContratoAgricolaMovimentoMapper extends GenericMapper<ContratoAgricolaMovimento, ContratoAgricolaMovimentoDTO> {

	private ContratoAgricolaRepository contratoAgricolaRepository;
	private OperacaoInternaRepository operacaoInternaRepository;

	public ContratoAgricolaMovimentoMapper(ContratoAgricolaMovimentoRepository contratoAgricolaMovimentoRepository,
			ContratoAgricolaRepository contratoAgricolaRepository, OperacaoInternaRepository operacaoInternaRepository) {
		super(contratoAgricolaMovimentoRepository, ContratoAgricolaMovimento::new, ContratoAgricolaMovimentoDTO::new);
		this.contratoAgricolaRepository = contratoAgricolaRepository;
		this.operacaoInternaRepository = operacaoInternaRepository;
	}

	@Override
	protected void setValuesToEntity(ContratoAgricolaMovimentoDTO dto, ContratoAgricolaMovimento entity) {
		entity.setId(dto.getId());
		if (dto.getContratoAgricolaId() != null) {
			entity.setContratoAgricola(contratoAgricolaRepository.findById(dto.getContratoAgricolaId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado romaneio agrícola com id " + dto.getContratoAgricolaId())));
		}
		if (dto.getOperacaoInternaId() != null) {
			entity.setOperacaoInterna(operacaoInternaRepository.findById(dto.getOperacaoInternaId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado operação interna com id " + dto.getOperacaoInternaId())));
		}
		entity.setNumeroNota(dto.getNumeroNota());
		entity.setDataMovimento(dto.getDataMovimento());
		entity.setQuantidade(dto.getQuantidade());
		entity.setValor(dto.getValor());

	}

	@Override
	protected void setValuesToDto(ContratoAgricolaMovimento entity, ContratoAgricolaMovimentoDTO dto) {
		dto.setId(entity.getId());
		dto.setContratoAgricolaId(entity.getContratoAgricola().getId());
		dto.setOperacaoInternaId(entity.getOperacaoInterna().getId());
		dto.setOperacaoInternaNome(entity.getOperacaoInterna().getNome());
		dto.setNumeroNota(entity.getNumeroNota());
		dto.setDataMovimento(entity.getDataMovimento());
		dto.setQuantidade(entity.getQuantidade());
		dto.setValor(entity.getValor());
	}
}

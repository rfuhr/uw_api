package br.com.ultraworks.erp.api.tabela.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.agricola.domain.identificacaodocumentoagricola.IdentificacaoDocumentoAgricola;
import br.com.ultraworks.erp.api.agricola.repository.TipoContratoAgricolaRepository;
import br.com.ultraworks.erp.api.agricola.repository.TipoPrecoAgricolaRepository;
import br.com.ultraworks.erp.api.tabela.domain.operacaointernaagricola.OperacaoInternaAgricola;
import br.com.ultraworks.erp.api.tabela.domain.operacaointernaagricola.OperacaoInternaAgricolaDTO;
import br.com.ultraworks.erp.api.tabela.repository.OperacaoInternaAgricolaRepository;
import br.com.ultraworks.erp.api.tabela.repository.OperacaoInternaRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class OperacaoInternaAgricolaMapper extends GenericMapper<OperacaoInternaAgricola, OperacaoInternaAgricolaDTO> {

	private OperacaoInternaRepository operacaoInternaRepository;
	private TipoPrecoAgricolaRepository tipoPrecoAgricolaRepository;
	private TipoContratoAgricolaRepository tipoContratoAgricolaRepository;

	public OperacaoInternaAgricolaMapper(OperacaoInternaAgricolaRepository repository,
			OperacaoInternaRepository operacaoInternaRepository, 
			TipoPrecoAgricolaRepository tipoPrecoAgricolaRepository,
			TipoContratoAgricolaRepository tipoContratoAgricolaRepository) {
		super(repository, OperacaoInternaAgricola::new, OperacaoInternaAgricolaDTO::new);
		this.operacaoInternaRepository = operacaoInternaRepository;
		this.tipoPrecoAgricolaRepository = tipoPrecoAgricolaRepository;
		this.tipoContratoAgricolaRepository = tipoContratoAgricolaRepository;
	}

	@Override
	protected void setValuesToEntity(OperacaoInternaAgricolaDTO dto, OperacaoInternaAgricola entity) {
		entity.setId(dto.getId());
		if (dto.getOperacaoInternaId() != null) {
			entity.setOperacaoInterna(operacaoInternaRepository.findById(dto.getOperacaoInternaId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado operação interna com id " + dto.getOperacaoInternaId())));
		}
		entity.setIdentificacaoDocumentoAgricola(
				IdentificacaoDocumentoAgricola.fromValue(dto.getIdentificacaoDocumentoAgricola()));
		entity.setExigeNotaEntrada(dto.isExigeNotaEntrada());
		entity.setFixaAutomatico(dto.isFixaAutomatico());
		entity.setComplementoPrecoFixacao(dto.isComplementoPrecoFixacao());
		entity.setContratoAvista(dto.isContratoAvista());
		entity.setContratoAfixar(dto.isContratoAfixar());
		if (dto.getTipoPrecoAgricolaId() != null) {
			entity.setTipoPrecoAgricola(tipoPrecoAgricolaRepository.findById(dto.getTipoPrecoAgricolaId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado tipo preço agrícola com id " + dto.getTipoPrecoAgricolaId())));
		}
		if (dto.getTipoContratoAgricolaId() != null) {
			entity.setTipoContratoAgricola(tipoContratoAgricolaRepository.findById(dto.getTipoContratoAgricolaId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado tipo contrato agrícola com id " + dto.getTipoContratoAgricolaId())));
		}
	}

	@Override
	protected void setValuesToDto(OperacaoInternaAgricola entity, OperacaoInternaAgricolaDTO dto) {
		dto.setId(entity.getId());
		dto.setOperacaoInternaId(entity.getOperacaoInterna().getId());
		dto.setIdentificacaoDocumentoAgricola(entity.getIdentificacaoDocumentoAgricola().getValue());
		dto.setIdentificacaoDocumentoAgricolaNome(entity.getIdentificacaoDocumentoAgricola().getName());
		dto.setExigeNotaEntrada(entity.isExigeNotaEntrada());
		dto.setFixaAutomatico(entity.isFixaAutomatico());
		dto.setComplementoPrecoFixacao(entity.isComplementoPrecoFixacao());
		dto.setContratoAvista(entity.isContratoAvista());
		dto.setContratoAfixar(entity.isContratoAfixar());
		if (entity.getTipoPrecoAgricola() != null) {
			dto.setTipoPrecoAgricolaId(entity.getTipoPrecoAgricola().getId());
			dto.setTipoPrecoAgricolaNome(entity.getTipoPrecoAgricola().getNome());
		}
		if (entity.getTipoContratoAgricola() != null) {
			dto.setTipoContratoAgricolaId(entity.getTipoContratoAgricola().getId());
			dto.setTipoContratoAgricolaNome(entity.getTipoContratoAgricola().getNome());
		}
	}
}

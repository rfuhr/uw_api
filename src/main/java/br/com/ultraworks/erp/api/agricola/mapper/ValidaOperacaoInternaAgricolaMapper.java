package br.com.ultraworks.erp.api.agricola.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.agricola.domain.validaoperacaointernaagricola.ValidaOperacaoInternaAgricola;
import br.com.ultraworks.erp.api.agricola.domain.validaoperacaointernaagricola.ValidaOperacaoInternaAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.repository.GrupoOperacaoAgricolaRepository;
import br.com.ultraworks.erp.api.agricola.repository.TipoPrecoAgricolaRepository;
import br.com.ultraworks.erp.api.agricola.repository.ValidaOperacaoInternaAgricolaRepository;
import br.com.ultraworks.erp.api.estoque.repository.ItemRepository;
import br.com.ultraworks.erp.api.financeiro.repository.FatoGeradorRepository;
import br.com.ultraworks.erp.api.tabela.repository.OperacaoInternaRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ValidaOperacaoInternaAgricolaMapper
		extends GenericMapper<ValidaOperacaoInternaAgricola, ValidaOperacaoInternaAgricolaDTO> {

	private ItemRepository itemRepository;
	private OperacaoInternaRepository operacaoInternaRepository;
	private GrupoOperacaoAgricolaRepository grupoOperacaoAgricolaRepository;
	private TipoPrecoAgricolaRepository tipoPrecoAgricolaRepository;
	private FatoGeradorRepository fatoGeradorRepository;

	public ValidaOperacaoInternaAgricolaMapper(ValidaOperacaoInternaAgricolaRepository repository,
			ItemRepository itemRepository, OperacaoInternaRepository operacaoInternaRepository,
			GrupoOperacaoAgricolaRepository grupoOperacaoAgricolaRepository,
			TipoPrecoAgricolaRepository tipoPrecoAgricolaRepository, FatoGeradorRepository fatoGeradorRepository) {
		super(repository, ValidaOperacaoInternaAgricola::new, ValidaOperacaoInternaAgricolaDTO::new);
		this.itemRepository = itemRepository;
		this.operacaoInternaRepository = operacaoInternaRepository;
		this.grupoOperacaoAgricolaRepository = grupoOperacaoAgricolaRepository;
		this.tipoPrecoAgricolaRepository = tipoPrecoAgricolaRepository;
		this.fatoGeradorRepository = fatoGeradorRepository;
	}

	@Override
	protected void setValuesToEntity(ValidaOperacaoInternaAgricolaDTO dto, ValidaOperacaoInternaAgricola entity) {
		entity.setId(dto.getId());
		if (dto.getItemId() != null) {
			entity.setItem(itemRepository.findById(dto.getItemId())
					.orElseThrow(() -> new RegisterNotFoundException("Não encontrado item com id " + dto.getItemId())));
		}
		if (dto.getOperacaoInternaId() != null) {
			entity.setOperacaoInterna(operacaoInternaRepository.findById(dto.getOperacaoInternaId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado operação interna com id " + dto.getOperacaoInternaId())));
		}
		if (dto.getGrupoOperacaoAgricolaId() != null) {
			entity.setGrupoOperacaoAgricola(grupoOperacaoAgricolaRepository.findById(dto.getGrupoOperacaoAgricolaId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado grupo de operação agrícola com id " + dto.getGrupoOperacaoAgricolaId())));
		}
		if (dto.getTipoPrecoAgricolaId() != null) {
			entity.setTipoPrecoAgricola(tipoPrecoAgricolaRepository.findById(dto.getTipoPrecoAgricolaId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado tipo de preço agrícola com id " + dto.getTipoPrecoAgricolaId())));
		}
		if (dto.getFatoGeradorContratoId() != null) {
			entity.setFatoGeradorContrato(fatoGeradorRepository.findById(dto.getFatoGeradorContratoId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado fato gerador com id " + dto.getFatoGeradorContratoId())));
		}
		entity.setDataInicioVigencia(dto.getDataInicioVigencia());
		entity.setDataFinalVigencia(dto.getDataFinalVigencia());
	}

	@Override
	protected void setValuesToDto(ValidaOperacaoInternaAgricola entity, ValidaOperacaoInternaAgricolaDTO dto) {
		dto.setId(entity.getId());
		dto.setItemId(entity.getItem().getId());
		dto.setOperacaoInternaId(entity.getOperacaoInterna().getId());
		dto.setGrupoOperacaoAgricolaId(entity.getGrupoOperacaoAgricola().getId());
		dto.setTipoPrecoAgricolaId(entity.getTipoPrecoAgricola().getId());
		if (entity.getFatoGeradorContrato() != null) {
			dto.setFatoGeradorContratoId(entity.getFatoGeradorContrato().getId());
			dto.setFatoGeradorContratoNome(entity.getFatoGeradorContrato().getNome());
		}
		dto.setDataInicioVigencia(entity.getDataInicioVigencia());
		dto.setDataFinalVigencia(entity.getDataFinalVigencia());

		dto.setItemNome(entity.getItem().getNome());
		dto.setOperacaoInternaNome(entity.getOperacaoInterna().getNome());
		dto.setGrupoOperacaoAgricolaNome(entity.getGrupoOperacaoAgricola().getNome());
		dto.setTipoPrecoAgricolaNome(entity.getTipoPrecoAgricola().getNome());
	}
}

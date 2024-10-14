package br.com.ultraworks.erp.api.agricola.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.agricola.domain.validacalculoagricola.ValidaCalculoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.validacalculoagricola.ValidaCalculoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.repository.GrupoOperacaoAgricolaRepository;
import br.com.ultraworks.erp.api.agricola.repository.TipoCalculoAgricolaRepository;
import br.com.ultraworks.erp.api.agricola.repository.ValidaCalculoAgricolaRepository;
import br.com.ultraworks.erp.api.estoque.repository.ItemRepository;
import br.com.ultraworks.erp.api.tabela.repository.OperacaoInternaRepository;
import br.com.ultraworks.erp.api.tabela.repository.RegraAtividadeRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ValidaCalculoAgricolaMapper extends GenericMapper<ValidaCalculoAgricola, ValidaCalculoAgricolaDTO> {

	private ItemRepository itemRepository;
	private TipoCalculoAgricolaRepository tipoCalculoAgricolaRepository;
	private OperacaoInternaRepository operacaoInternaRepository;
	private GrupoOperacaoAgricolaRepository grupoOperacaoAgricolaRepository;
	private RegraAtividadeRepository regraAtividadeRepository;

	public ValidaCalculoAgricolaMapper(ValidaCalculoAgricolaRepository repository, ItemRepository itemRepository,
			TipoCalculoAgricolaRepository tipoCalculoAgricolaRepository,
			OperacaoInternaRepository operacaoInternaRepository,
			GrupoOperacaoAgricolaRepository grupoOperacaoAgricolaRepository,
			RegraAtividadeRepository regraAtividadeRepository) {
		super(repository, ValidaCalculoAgricola::new, ValidaCalculoAgricolaDTO::new);
		this.itemRepository = itemRepository;
		this.tipoCalculoAgricolaRepository = tipoCalculoAgricolaRepository;
		this.operacaoInternaRepository = operacaoInternaRepository;
		this.grupoOperacaoAgricolaRepository = grupoOperacaoAgricolaRepository;
		this.regraAtividadeRepository = regraAtividadeRepository;
	}

	@Override
	protected void setValuesToEntity(ValidaCalculoAgricolaDTO dto, ValidaCalculoAgricola entity) {
		entity.setId(dto.getId());
		if (dto.getItemId() != null) {
			entity.setItem(itemRepository.findById(dto.getItemId())
					.orElseThrow(() -> new RegisterNotFoundException("Não encontrado item com id " + dto.getItemId())));
		}
		if (dto.getTipoCalculoAgricolaId() != null) {
			entity.setTipoCalculoAgricola(tipoCalculoAgricolaRepository.findById(dto.getTipoCalculoAgricolaId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado tipo de cálculo agrícola com id " + dto.getTipoCalculoAgricolaId())));
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
		if (dto.getRegraAtividadeId() != null) {
			entity.setRegraAtividade(regraAtividadeRepository.findById(dto.getRegraAtividadeId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado regra de atividade com id " + dto.getRegraAtividadeId())));
		}
		entity.setDataInicioVigencia(dto.getDataInicioVigencia());
		entity.setDataFinalVigencia(dto.getDataFinalVigencia());
	}

	@Override
	protected void setValuesToDto(ValidaCalculoAgricola entity, ValidaCalculoAgricolaDTO dto) {
		dto.setId(entity.getId());
		dto.setItemId(entity.getItem().getId());
		dto.setTipoCalculoAgricolaId(entity.getTipoCalculoAgricola().getId());
		dto.setOperacaoInternaId(entity.getOperacaoInterna().getId());
		dto.setGrupoOperacaoAgricolaId(entity.getGrupoOperacaoAgricola().getId());
		dto.setRegraAtividadeId(entity.getRegraAtividade().getId());
		dto.setDataInicioVigencia(entity.getDataInicioVigencia());
		dto.setDataFinalVigencia(entity.getDataFinalVigencia());

		dto.setItemNome(entity.getItem().getNome());
		dto.setTipoCalculoAgricolaNome(entity.getTipoCalculoAgricola().getNome());
		dto.setOperacaoInternaNome(entity.getOperacaoInterna().getNome());
		dto.setGrupoOperacaoAgricolaNome(entity.getGrupoOperacaoAgricola().getNome());
		dto.setRegraAtividadeNome(entity.getRegraAtividade().getNome());
	}
}

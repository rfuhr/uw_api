package br.com.ultraworks.erp.api.agricola.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.agricola.domain.taxacalculoagricola.TaxaCalculoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.taxacalculoagricola.TaxaCalculoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.domain.tipotaxaagricola.TipoTaxaAgricola;
import br.com.ultraworks.erp.api.agricola.repository.TaxaCalculoAgricolaRepository;
import br.com.ultraworks.erp.api.agricola.repository.TipoCalculoAgricolaRepository;
import br.com.ultraworks.erp.api.estoque.repository.ItemRepository;
import br.com.ultraworks.erp.api.tabela.repository.RegraAtividadeRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class TaxaCalculoAgricolaMapper extends GenericMapper<TaxaCalculoAgricola, TaxaCalculoAgricolaDTO> {

	private ItemRepository itemRepository;
	private TipoCalculoAgricolaRepository tipoCalculoAgricolaRepository;
	private RegraAtividadeRepository regraAtividadeRepository;

	public TaxaCalculoAgricolaMapper(TaxaCalculoAgricolaRepository repository, ItemRepository itemRepository,
			TipoCalculoAgricolaRepository tipoCalculoAgricolaRepository,
			RegraAtividadeRepository regraAtividadeRepository) {
		super(repository, TaxaCalculoAgricola::new, TaxaCalculoAgricolaDTO::new);
		this.itemRepository = itemRepository;
		this.tipoCalculoAgricolaRepository = tipoCalculoAgricolaRepository;
		this.regraAtividadeRepository = regraAtividadeRepository;
	}

	@Override
	protected void setValuesToEntity(TaxaCalculoAgricolaDTO dto, TaxaCalculoAgricola entity) {
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
		if (dto.getRegraAtividadeId() != null) {
			entity.setRegraAtividade(regraAtividadeRepository.findById(dto.getRegraAtividadeId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado regra de atividade com id " + dto.getRegraAtividadeId())));
		}
		entity.setFaixaLimite(dto.getFaixaLimite());
		entity.setTipoTaxaAgricola(TipoTaxaAgricola.fromValue(dto.getTipoTaxaAgricola()));
		entity.setFatorCalculo(dto.getFatorCalculo());
		entity.setDataInicioVigencia(dto.getDataInicioVigencia());
		entity.setDataFinalVigencia(dto.getDataFinalVigencia());
	}

	@Override
	protected void setValuesToDto(TaxaCalculoAgricola entity, TaxaCalculoAgricolaDTO dto) {
		dto.setId(entity.getId());
		dto.setItemId(entity.getItem().getId());
		dto.setTipoCalculoAgricolaId(entity.getTipoCalculoAgricola().getId());
		dto.setRegraAtividadeId(entity.getRegraAtividade().getId());
		dto.setFaixaLimite(entity.getFaixaLimite());
		dto.setTipoTaxaAgricola(entity.getTipoTaxaAgricola().getValue());
		dto.setFatorCalculo(entity.getFatorCalculo());
		dto.setDataInicioVigencia(entity.getDataInicioVigencia());
		dto.setDataFinalVigencia(entity.getDataFinalVigencia());

		dto.setItemNome(entity.getItem().getNome());
		dto.setTipoCalculoAgricolaNome(entity.getTipoCalculoAgricola().getNome());
		dto.setRegraAtividadeNome(entity.getRegraAtividade().getNome());
		dto.setTipoTaxaAgricolaNome(entity.getTipoTaxaAgricola().getName());
	}
}

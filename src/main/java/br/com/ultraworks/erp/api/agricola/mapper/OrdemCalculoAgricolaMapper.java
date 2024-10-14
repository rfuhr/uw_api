package br.com.ultraworks.erp.api.agricola.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.agricola.domain.identificacaodocumentoagricola.IdentificacaoDocumentoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.ordemcalculoagricola.OrdemCalculoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.ordemcalculoagricola.OrdemCalculoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.repository.OrdemCalculoAgricolaRepository;
import br.com.ultraworks.erp.api.agricola.repository.TipoCalculoAgricolaRepository;
import br.com.ultraworks.erp.api.estoque.repository.ItemRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class OrdemCalculoAgricolaMapper extends GenericMapper<OrdemCalculoAgricola, OrdemCalculoAgricolaDTO> {

	private ItemRepository itemRepository;
	private TipoCalculoAgricolaRepository tipoCalculoAgricolaRepository;

	public OrdemCalculoAgricolaMapper(OrdemCalculoAgricolaRepository repository, ItemRepository itemRepository,
			TipoCalculoAgricolaRepository tipoCalculoAgricolaRepository) {
		super(repository, OrdemCalculoAgricola::new, OrdemCalculoAgricolaDTO::new);
		this.itemRepository = itemRepository;
		this.tipoCalculoAgricolaRepository = tipoCalculoAgricolaRepository;
	}

	@Override
	protected void setValuesToEntity(OrdemCalculoAgricolaDTO dto, OrdemCalculoAgricola entity) {
		entity.setId(dto.getId());
		if (dto.getItemId() != null) {
			entity.setItem(itemRepository.findById(dto.getItemId())
					.orElseThrow(() -> new RegisterNotFoundException("Não encontrado item com id " + dto.getItemId())));
		}
		entity.setIdentificacaoDocumentoAgricola(
				IdentificacaoDocumentoAgricola.fromValue(dto.getIdentificacaoDocumentoAgricola()));
		if (dto.getTipoCalculoAgricolaId() != null) {
			entity.setTipoCalculoAgricola(tipoCalculoAgricolaRepository.findById(dto.getTipoCalculoAgricolaId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado tipo de cálculo com id " + dto.getTipoCalculoAgricolaId())));
		}
		entity.setOrdem(dto.getOrdem());
		entity.setDataInicioVigencia(dto.getDataInicioVigencia());
		entity.setDataFinalVigencia(dto.getDataFinalVigencia());
	}

	@Override
	protected void setValuesToDto(OrdemCalculoAgricola entity, OrdemCalculoAgricolaDTO dto) {
		dto.setId(entity.getId());
		dto.setItemId(entity.getItem().getId());
		dto.setIdentificacaoDocumentoAgricola(entity.getIdentificacaoDocumentoAgricola().getValue());
		dto.setTipoCalculoAgricolaId(entity.getTipoCalculoAgricola().getId());
		dto.setOrdem(entity.getOrdem());
		dto.setDataInicioVigencia(entity.getDataInicioVigencia());
		dto.setDataFinalVigencia(entity.getDataFinalVigencia());

		dto.setItemNome(entity.getItem().getNome());
		dto.setIdentificacaoDocumentoAgricolaNome(entity.getIdentificacaoDocumentoAgricola().getName());
		dto.setTipoCalculoAgricolaNome(entity.getTipoCalculoAgricola().getNome());
	}
}

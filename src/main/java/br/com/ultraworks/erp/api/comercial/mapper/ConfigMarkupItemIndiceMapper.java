package br.com.ultraworks.erp.api.comercial.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.comercial.domain.configmarkupitemindice.ConfigMarkupItemIndice;
import br.com.ultraworks.erp.api.comercial.domain.configmarkupitemindice.ConfigMarkupItemIndiceDTO;
import br.com.ultraworks.erp.api.comercial.repository.ConfigMarkupItemIndiceRepository;
import br.com.ultraworks.erp.api.comercial.repository.ConfigMarkupItemRepository;
import br.com.ultraworks.erp.api.comercial.repository.IndiceMarkupRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ConfigMarkupItemIndiceMapper extends GenericMapper<ConfigMarkupItemIndice, ConfigMarkupItemIndiceDTO> {

	ConfigMarkupItemRepository configMarkupItemRepository;
	IndiceMarkupRepository indiceMarkupRepository;
	
	public ConfigMarkupItemIndiceMapper(ConfigMarkupItemIndiceRepository repository,
			ConfigMarkupItemRepository configMarkupItemRepository,
			IndiceMarkupRepository indiceMarkupRepository) {
		super(repository, ConfigMarkupItemIndice::new, ConfigMarkupItemIndiceDTO::new);
		this.configMarkupItemRepository = configMarkupItemRepository;
		this.indiceMarkupRepository = indiceMarkupRepository;
	}

	@Override
	protected void setValuesToEntity(ConfigMarkupItemIndiceDTO dto, ConfigMarkupItemIndice entity) {
		entity.setId(dto.getId());
		entity.setDataInicioVigencia(dto.getDataInicioVigencia());
		entity.setDataFinalVigencia(dto.getDataFinalVigencia());
		if (dto.getConfigMarkupItemId() != null) {
			entity.setConfigMarkupItem(configMarkupItemRepository.findById(dto.getConfigMarkupItemId())
					.orElseThrow(() -> new RegisterNotFoundException("Não encontrado Configuração de Mark Up do Item com id " + dto.getConfigMarkupItemId())));			
		}
		entity.setIndiceMarkup(indiceMarkupRepository.findById(dto.getIndiceMarkupId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado o Índice de Mark Up com id " + dto.getIndiceMarkupId())));	
		entity.setPercentual(dto.getPercentual());
	}

	@Override
	protected void setValuesToDto(ConfigMarkupItemIndice entity, ConfigMarkupItemIndiceDTO dto) {
		dto.setId(entity.getId());
		dto.setDataInicioVigencia(entity.getDataInicioVigencia());
		dto.setDataFinalVigencia(entity.getDataFinalVigencia());
		dto.setConfigMarkupItemId(entity.getConfigMarkupItem().getId());
		if (entity.getIndiceMarkup() != null) {
			dto.setIndiceMarkupCodigo(entity.getIndiceMarkup().getCodigo());
			dto.setIndiceMarkupNome(entity.getIndiceMarkup().getNome());
			dto.setIndiceMarkupId(entity.getIndiceMarkup().getId());
		}
		dto.setPercentual(entity.getPercentual());
	}
}

package br.com.ultraworks.erp.api.comercial.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.comercial.domain.configmarkupplanoitemindice.ConfigMarkupPlanoItemIndice;
import br.com.ultraworks.erp.api.comercial.domain.configmarkupplanoitemindice.ConfigMarkupPlanoItemIndiceDTO;
import br.com.ultraworks.erp.api.comercial.repository.ConfigMarkupPlanoItemIndiceRepository;
import br.com.ultraworks.erp.api.comercial.repository.ConfigMarkupPlanoItemRepository;
import br.com.ultraworks.erp.api.comercial.repository.IndiceMarkupRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ConfigMarkupPlanoItemIndiceMapper extends GenericMapper<ConfigMarkupPlanoItemIndice, ConfigMarkupPlanoItemIndiceDTO> {

	ConfigMarkupPlanoItemRepository configMarkupPlanoItemRepository;
	IndiceMarkupRepository indiceMarkupRepository;
	
	public ConfigMarkupPlanoItemIndiceMapper(ConfigMarkupPlanoItemIndiceRepository repository,
			ConfigMarkupPlanoItemRepository configMarkupPlanoItemRepository,
			IndiceMarkupRepository indiceMarkupRepository) {
		super(repository, ConfigMarkupPlanoItemIndice::new, ConfigMarkupPlanoItemIndiceDTO::new);
		this.configMarkupPlanoItemRepository = configMarkupPlanoItemRepository;
		this.indiceMarkupRepository = indiceMarkupRepository;
	}

	@Override
	protected void setValuesToEntity(ConfigMarkupPlanoItemIndiceDTO dto, ConfigMarkupPlanoItemIndice entity) {
		entity.setId(dto.getId());
		entity.setDataInicioVigencia(dto.getDataInicioVigencia());
		entity.setDataFinalVigencia(dto.getDataFinalVigencia());
		if (dto.getConfigMarkupPlanoItemId() != null) {
			entity.setConfigMarkupPlanoItem(configMarkupPlanoItemRepository.findById(dto.getConfigMarkupPlanoItemId())
					.orElseThrow(() -> new RegisterNotFoundException("Não encontrado Configuração de Mark Up do Plano de Classificação do Item com id " + dto.getConfigMarkupPlanoItemId())));			
		}
		entity.setIndiceMarkup(indiceMarkupRepository.findById(dto.getIndiceMarkupId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado o Índice de Mark Up com id " + dto.getIndiceMarkupId())));	
		entity.setPercentual(dto.getPercentual());
	}

	@Override
	protected void setValuesToDto(ConfigMarkupPlanoItemIndice entity, ConfigMarkupPlanoItemIndiceDTO dto) {
		dto.setId(entity.getId());
		dto.setDataInicioVigencia(entity.getDataInicioVigencia());
		dto.setDataFinalVigencia(entity.getDataFinalVigencia());
		dto.setConfigMarkupPlanoItemId(entity.getConfigMarkupPlanoItem().getId());
		if (entity.getIndiceMarkup() != null) {
			dto.setIndiceMarkupCodigo(entity.getIndiceMarkup().getCodigo());
			dto.setIndiceMarkupNome(entity.getIndiceMarkup().getNome());
			dto.setIndiceMarkupId(entity.getIndiceMarkup().getId());
		}
		dto.setPercentual(entity.getPercentual());
	}
}

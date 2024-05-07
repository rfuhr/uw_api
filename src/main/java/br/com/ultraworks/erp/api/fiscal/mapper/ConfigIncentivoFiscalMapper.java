package br.com.ultraworks.erp.api.fiscal.mapper;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.fiscal.domain.configincentivofiscal.ConfigIncentivoFiscal;
import br.com.ultraworks.erp.api.fiscal.domain.configincentivofiscal.ConfigIncentivoFiscalDTO;
import br.com.ultraworks.erp.api.fiscal.repository.ConfigIncentivoFiscalRepository;
import br.com.ultraworks.erp.api.fiscal.repository.TipoIncentivoFiscalRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ConfigIncentivoFiscalMapper extends GenericMapper<ConfigIncentivoFiscal, ConfigIncentivoFiscalDTO> {

	TipoIncentivoFiscalRepository tipoIncentivoFiscalRepository;
	ConfigIncentivoFiscalParceiroMapper configIncentivoFiscalParceiroMapper;
	
	public ConfigIncentivoFiscalMapper(ConfigIncentivoFiscalRepository ConfigIncentivoFiscalRepository, TipoIncentivoFiscalRepository tipoIncentivoFiscalRepository,
			ConfigIncentivoFiscalParceiroMapper configIncentivoFiscalParceiroMapper) {
		super(ConfigIncentivoFiscalRepository, ConfigIncentivoFiscal::new, ConfigIncentivoFiscalDTO::new);
		this.tipoIncentivoFiscalRepository = tipoIncentivoFiscalRepository;
		this.configIncentivoFiscalParceiroMapper = configIncentivoFiscalParceiroMapper;
    }

	@Override
	protected void setValuesToEntity(ConfigIncentivoFiscalDTO dto, ConfigIncentivoFiscal entity) {
		entity.setId(dto.getId());
		entity.setDataInicioVigencia(dto.getDataInicioVigencia());
		entity.setDataFinalVigencia(dto.getDataFinalVigencia());
		entity.setTipoIncentivoFiscal(tipoIncentivoFiscalRepository.findById(dto.getTipoIncentivoFiscalId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado Tipo de Incentivo Fiscal com id " + dto.getTipoIncentivoFiscalId())));
		if (dto.getConfigIncentivoFiscalParceiros() != null && dto.getConfigIncentivoFiscalParceiros().size() > 0) {
			entity.setConfigIncentivoFiscalParceiros(new ArrayList<>());
			entity.getConfigIncentivoFiscalParceiros().addAll(configIncentivoFiscalParceiroMapper.toEntity(dto.getConfigIncentivoFiscalParceiros()));
		}
	}

	@Override
	protected void setValuesToDto(ConfigIncentivoFiscal entity, ConfigIncentivoFiscalDTO dto) {
		dto.setId(entity.getId());
		dto.setDataInicioVigencia(entity.getDataInicioVigencia());
		dto.setDataFinalVigencia(entity.getDataFinalVigencia());
		if (entity.getTipoIncentivoFiscal() != null) {
			dto.setTipoIncentivoFiscalId(entity.getTipoIncentivoFiscal().getId());
			dto.setTipoIncentivoFiscalNome(entity.getTipoIncentivoFiscal().getNome());
			dto.setTipoIncentivoFiscalCodigo(entity.getTipoIncentivoFiscal().getCodigo());
		}
		dto.setConfigIncentivoFiscalParceiros(new ArrayList<>());
		if (entity.getConfigIncentivoFiscalParceiros() != null) {
			dto.getConfigIncentivoFiscalParceiros().addAll(configIncentivoFiscalParceiroMapper.toDto(entity.getConfigIncentivoFiscalParceiros()));
		}
	}	
}

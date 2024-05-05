package br.com.ultraworks.erp.api.fiscal.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscaltipoincentfiscal.ConfigMensagemFiscalTipoIncentFiscal;
import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscaltipoincentfiscal.ConfigMensagemFiscalTipoIncentFiscalDTO;
import br.com.ultraworks.erp.api.fiscal.repository.ConfigMensagemFiscalRepository;
import br.com.ultraworks.erp.api.fiscal.repository.ConfigMensagemFiscalTipoIncentFiscalRepository;
import br.com.ultraworks.erp.api.fiscal.repository.TipoIncentivoFiscalRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ConfigMensagemFiscalTipoIncentFiscalMapper extends GenericMapper<ConfigMensagemFiscalTipoIncentFiscal, ConfigMensagemFiscalTipoIncentFiscalDTO> {

	ConfigMensagemFiscalRepository configMensagemFiscalRepository;
	TipoIncentivoFiscalRepository tipoIncentivoFiscalRepository;
	
	public ConfigMensagemFiscalTipoIncentFiscalMapper(ConfigMensagemFiscalTipoIncentFiscalRepository ConfigMensagemFiscalTipoIncentFiscalRepository, ConfigMensagemFiscalRepository configMensagemFiscalRepository,
			TipoIncentivoFiscalRepository tipoIncentivoFiscalRepository) {
		super(ConfigMensagemFiscalTipoIncentFiscalRepository, ConfigMensagemFiscalTipoIncentFiscal::new, ConfigMensagemFiscalTipoIncentFiscalDTO::new);
		this.configMensagemFiscalRepository = configMensagemFiscalRepository;
		this.tipoIncentivoFiscalRepository = tipoIncentivoFiscalRepository;
    }

	@Override
	protected void setValuesToEntity(ConfigMensagemFiscalTipoIncentFiscalDTO dto, ConfigMensagemFiscalTipoIncentFiscal entity) {
		entity.setId(dto.getId());
		entity.setDataInicioVigencia(dto.getDataInicioVigencia());
		entity.setDataFinalVigencia(dto.getDataFinalVigencia());
		entity.setConfigMensagemFiscal(configMensagemFiscalRepository.findById(dto.getConfigMensagemFiscalId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado Configuração da Mensagem Fiscal com id " + dto.getConfigMensagemFiscalId())));
		entity.setTipoIncentivoFiscal(tipoIncentivoFiscalRepository.findById(dto.getTipoIncentivoFiscalId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado o Tipo de Incentivo Fiscal com id " + dto.getTipoIncentivoFiscalId())));
	}

	@Override
	protected void setValuesToDto(ConfigMensagemFiscalTipoIncentFiscal entity, ConfigMensagemFiscalTipoIncentFiscalDTO dto) {
		dto.setId(entity.getId());
		dto.setConfigMensagemFiscalId(entity.getConfigMensagemFiscal().getId());
		dto.setTipoIncentivoFiscalId(entity.getTipoIncentivoFiscal().getId());
		dto.setTipoIncentivoFiscalNome(entity.getTipoIncentivoFiscal().getNome());
		dto.setTipoIncentivoFiscalCodigo(entity.getTipoIncentivoFiscal().getCodigo());
		dto.setDataInicioVigencia(entity.getDataInicioVigencia());
		dto.setDataFinalVigencia(entity.getDataFinalVigencia());
	}	
}

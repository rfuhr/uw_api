package br.com.ultraworks.erp.api.fiscal.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalsituactrib.ConfigMensagemFiscalSituacTrib;
import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalsituactrib.ConfigMensagemFiscalSituacTribDTO;
import br.com.ultraworks.erp.api.fiscal.repository.ConfigMensagemFiscalRepository;
import br.com.ultraworks.erp.api.fiscal.repository.ConfigMensagemFiscalSituacTribRepository;
import br.com.ultraworks.erp.api.fiscal.repository.SituacaoTributariaRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ConfigMensagemFiscalSituacTribMapper extends GenericMapper<ConfigMensagemFiscalSituacTrib, ConfigMensagemFiscalSituacTribDTO> {

	ConfigMensagemFiscalRepository configMensagemFiscalRepository;
	SituacaoTributariaRepository situacaoTributariaRepository;
	
	public ConfigMensagemFiscalSituacTribMapper(ConfigMensagemFiscalSituacTribRepository ConfigMensagemFiscalSituacTribRepository, ConfigMensagemFiscalRepository configMensagemFiscalRepository,
			SituacaoTributariaRepository situacaoTributariaRepository) {
		super(ConfigMensagemFiscalSituacTribRepository, ConfigMensagemFiscalSituacTrib::new, ConfigMensagemFiscalSituacTribDTO::new);
		this.configMensagemFiscalRepository = configMensagemFiscalRepository;
		this.situacaoTributariaRepository = situacaoTributariaRepository;
    }

	@Override
	protected void setValuesToEntity(ConfigMensagemFiscalSituacTribDTO dto, ConfigMensagemFiscalSituacTrib entity) {
		entity.setId(dto.getId());
		entity.setDataInicioVigencia(dto.getDataInicioVigencia());
		entity.setDataFinalVigencia(dto.getDataFinalVigencia());
		if (dto.getConfigMensagemFiscalId() != null) {
			entity.setConfigMensagemFiscal(configMensagemFiscalRepository.findById(dto.getConfigMensagemFiscalId())
					.orElseThrow(() -> new RegisterNotFoundException("Não encontrado Configuração da Mensagem Fiscal com id " + dto.getConfigMensagemFiscalId())));
		}
		entity.setSituacaoTributaria(situacaoTributariaRepository.findById(dto.getSituacaoTributariaId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrada a Situação Tributária com id " + dto.getSituacaoTributariaId())));
	}

	@Override
	protected void setValuesToDto(ConfigMensagemFiscalSituacTrib entity, ConfigMensagemFiscalSituacTribDTO dto) {
		dto.setId(entity.getId());
		dto.setConfigMensagemFiscalId(entity.getConfigMensagemFiscal().getId());
		dto.setSituacaoTributariaId(entity.getSituacaoTributaria().getId());
		dto.setSituacaoTributariaTipoTributo(entity.getSituacaoTributaria().getTipoTributo().getValue());
		dto.setSituacaoTributariaNome(entity.getSituacaoTributaria().getNome());
		dto.setSituacaoTributariaCodigo(entity.getSituacaoTributaria().getCodigo());
		dto.setDataInicioVigencia(entity.getDataInicioVigencia());
		dto.setDataFinalVigencia(entity.getDataFinalVigencia());
	}	
}

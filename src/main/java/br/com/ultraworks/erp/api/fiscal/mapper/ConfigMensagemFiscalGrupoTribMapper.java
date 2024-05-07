package br.com.ultraworks.erp.api.fiscal.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalgrupotrib.ConfigMensagemFiscalGrupoTrib;
import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalgrupotrib.ConfigMensagemFiscalGrupoTribDTO;
import br.com.ultraworks.erp.api.fiscal.repository.ConfigMensagemFiscalGrupoTribRepository;
import br.com.ultraworks.erp.api.fiscal.repository.ConfigMensagemFiscalRepository;
import br.com.ultraworks.erp.api.fiscal.repository.GrupoTributacaoRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ConfigMensagemFiscalGrupoTribMapper extends GenericMapper<ConfigMensagemFiscalGrupoTrib, ConfigMensagemFiscalGrupoTribDTO> {

	ConfigMensagemFiscalRepository configMensagemFiscalRepository;
	GrupoTributacaoRepository grupoTributacaoRepository;
	
	public ConfigMensagemFiscalGrupoTribMapper(ConfigMensagemFiscalGrupoTribRepository ConfigMensagemFiscalGrupoTribRepository, ConfigMensagemFiscalRepository configMensagemFiscalRepository,
			GrupoTributacaoRepository grupoTributacaoRepository) {
		super(ConfigMensagemFiscalGrupoTribRepository, ConfigMensagemFiscalGrupoTrib::new, ConfigMensagemFiscalGrupoTribDTO::new);
		this.configMensagemFiscalRepository = configMensagemFiscalRepository;
		this.grupoTributacaoRepository = grupoTributacaoRepository;
    }

	@Override
	protected void setValuesToEntity(ConfigMensagemFiscalGrupoTribDTO dto, ConfigMensagemFiscalGrupoTrib entity) {
		entity.setId(dto.getId());
		entity.setDataInicioVigencia(dto.getDataInicioVigencia());
		entity.setDataFinalVigencia(dto.getDataFinalVigencia());
		if (dto.getConfigMensagemFiscalId() != null) {
			entity.setConfigMensagemFiscal(configMensagemFiscalRepository.findById(dto.getConfigMensagemFiscalId())
					.orElseThrow(() -> new RegisterNotFoundException("Não encontrado Configuração da Mensagem Fiscal com id " + dto.getConfigMensagemFiscalId())));			
		}
		entity.setGrupoTributacao(grupoTributacaoRepository.findById(dto.getGrupoTributacaoId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado o Grupo de Tributação com id " + dto.getGrupoTributacaoId())));
	}

	@Override
	protected void setValuesToDto(ConfigMensagemFiscalGrupoTrib entity, ConfigMensagemFiscalGrupoTribDTO dto) {
		dto.setId(entity.getId());
		dto.setConfigMensagemFiscalId(entity.getConfigMensagemFiscal().getId());
		dto.setGrupoTributacaoId(entity.getGrupoTributacao().getId());
		dto.setGrupoTributacaoNome(entity.getGrupoTributacao().getNome());
		dto.setGrupoTributacaoCodigo(entity.getGrupoTributacao().getCodigo());
		dto.setDataInicioVigencia(entity.getDataInicioVigencia());
		dto.setDataFinalVigencia(entity.getDataFinalVigencia());
	}	
}

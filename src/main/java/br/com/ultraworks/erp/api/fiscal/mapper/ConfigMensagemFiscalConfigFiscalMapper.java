package br.com.ultraworks.erp.api.fiscal.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalconfigfiscal.ConfigMensagemFiscalConfigFiscal;
import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalconfigfiscal.ConfigMensagemFiscalConfigFiscalDTO;
import br.com.ultraworks.erp.api.fiscal.repository.ConfigMensagemFiscalConfigFiscalRepository;
import br.com.ultraworks.erp.api.fiscal.repository.ConfigMensagemFiscalRepository;
import br.com.ultraworks.erp.api.fiscal.repository.ConfiguracaoFiscalRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ConfigMensagemFiscalConfigFiscalMapper extends GenericMapper<ConfigMensagemFiscalConfigFiscal, ConfigMensagemFiscalConfigFiscalDTO> {

	ConfigMensagemFiscalRepository configMensagemFiscalRepository;
	ConfiguracaoFiscalRepository configuracaoFiscalRepository;
	
	public ConfigMensagemFiscalConfigFiscalMapper(ConfigMensagemFiscalConfigFiscalRepository ConfigMensagemFiscalConfigFiscalRepository, ConfigMensagemFiscalRepository configMensagemFiscalRepository,
			ConfiguracaoFiscalRepository configuracaoFiscalRepository) {
		super(ConfigMensagemFiscalConfigFiscalRepository, ConfigMensagemFiscalConfigFiscal::new, ConfigMensagemFiscalConfigFiscalDTO::new);
		this.configMensagemFiscalRepository = configMensagemFiscalRepository;
		this.configuracaoFiscalRepository = configuracaoFiscalRepository;
    }

	@Override
	protected void setValuesToEntity(ConfigMensagemFiscalConfigFiscalDTO dto, ConfigMensagemFiscalConfigFiscal entity) {
		entity.setId(dto.getId());
		entity.setDataInicioVigencia(dto.getDataInicioVigencia());
		entity.setDataFinalVigencia(dto.getDataFinalVigencia());
		if (dto.getConfigMensagemFiscalId() != null) {
			entity.setConfigMensagemFiscal(configMensagemFiscalRepository.findById(dto.getConfigMensagemFiscalId())
					.orElseThrow(() -> new RegisterNotFoundException("Não encontrado Configuração da Mensagem Fiscal com id " + dto.getConfigMensagemFiscalId())));			
		}
		entity.setConfiguracaoFiscal(configuracaoFiscalRepository.findById(dto.getConfiguracaoFiscalId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrada a Configuação Fiscal com id " + dto.getConfiguracaoFiscalId())));
	}

	@Override
	protected void setValuesToDto(ConfigMensagemFiscalConfigFiscal entity, ConfigMensagemFiscalConfigFiscalDTO dto) {
		dto.setId(entity.getId());
		dto.setConfigMensagemFiscalId(entity.getConfigMensagemFiscal().getId());
		dto.setConfiguracaoFiscalId(entity.getConfiguracaoFiscal().getId());
		dto.setConfiguracaoFiscalRegime(entity.getConfiguracaoFiscal().getRegimeTributario().getNome());
		dto.setDataInicioVigencia(entity.getDataInicioVigencia());
		dto.setDataFinalVigencia(entity.getDataFinalVigencia());
		dto.setRegimeTributarioNome(entity.getConfiguracaoFiscal().getRegimeTributario().getNome());
		dto.setUfOrigemSigla(entity.getConfiguracaoFiscal().getUfOrigem().getSigla());
		dto.setUfDestinoSigla(entity.getConfiguracaoFiscal().getUfDestino().getSigla());
		dto.setIndicadorOperacao(entity.getConfiguracaoFiscal().getIndicadorOperacao().getValue());
		dto.setIcms(entity.getConfiguracaoFiscal().isIcms());
		dto.setIpi(entity.getConfiguracaoFiscal().isIpi());
		dto.setPis(entity.getConfiguracaoFiscal().isPis());
		dto.setCofins(entity.getConfiguracaoFiscal().isCofins());
	}	
}

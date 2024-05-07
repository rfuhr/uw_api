package br.com.ultraworks.erp.api.fiscal.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscaloperinterna.ConfigMensagemFiscalOperInterna;
import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscaloperinterna.ConfigMensagemFiscalOperInternaDTO;
import br.com.ultraworks.erp.api.fiscal.repository.ConfigMensagemFiscalOperInternaRepository;
import br.com.ultraworks.erp.api.fiscal.repository.ConfigMensagemFiscalRepository;
import br.com.ultraworks.erp.api.tabela.repository.OperacaoInternaRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ConfigMensagemFiscalOperInternaMapper extends GenericMapper<ConfigMensagemFiscalOperInterna, ConfigMensagemFiscalOperInternaDTO> {

	ConfigMensagemFiscalRepository configMensagemFiscalRepository;
	OperacaoInternaRepository operacaoInternaRepository;
	
	public ConfigMensagemFiscalOperInternaMapper(ConfigMensagemFiscalOperInternaRepository ConfigMensagemFiscalOperInternaRepository, ConfigMensagemFiscalRepository configMensagemFiscalRepository,
			OperacaoInternaRepository operacaoInternaRepository) {
		super(ConfigMensagemFiscalOperInternaRepository, ConfigMensagemFiscalOperInterna::new, ConfigMensagemFiscalOperInternaDTO::new);
		this.configMensagemFiscalRepository = configMensagemFiscalRepository;
		this.operacaoInternaRepository = operacaoInternaRepository;
    }

	@Override
	protected void setValuesToEntity(ConfigMensagemFiscalOperInternaDTO dto, ConfigMensagemFiscalOperInterna entity) {
		entity.setId(dto.getId());
		entity.setDataInicioVigencia(dto.getDataInicioVigencia());
		entity.setDataFinalVigencia(dto.getDataFinalVigencia());
		if (dto.getConfigMensagemFiscalId() != null) {
			entity.setConfigMensagemFiscal(configMensagemFiscalRepository.findById(dto.getConfigMensagemFiscalId())
					.orElseThrow(() -> new RegisterNotFoundException("Não encontrado Configuração da Mensagem Fiscal com id " + dto.getConfigMensagemFiscalId())));			
		}
		entity.setOperacaoInterna(operacaoInternaRepository.findById(dto.getOperacaoInternaId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrada a Operação Interna com id " + dto.getOperacaoInternaId())));
	}

	@Override
	protected void setValuesToDto(ConfigMensagemFiscalOperInterna entity, ConfigMensagemFiscalOperInternaDTO dto) {
		dto.setId(entity.getId());
		dto.setConfigMensagemFiscalId(entity.getConfigMensagemFiscal().getId());
		dto.setOperacaoInternaId(entity.getOperacaoInterna().getId());
		dto.setOperacaoInternaNome(entity.getOperacaoInterna().getNome());
		dto.setOperacaoInternaSigla(entity.getOperacaoInterna().getSigla());
		dto.setDataInicioVigencia(entity.getDataInicioVigencia());
		dto.setDataFinalVigencia(entity.getDataFinalVigencia());
	}	
}

package br.com.ultraworks.erp.api.fiscal.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscal.ConfigMensagemFiscal;
import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscal.ConfigMensagemFiscalDTO;
import br.com.ultraworks.erp.api.fiscal.repository.ConfigMensagemFiscalRepository;
import br.com.ultraworks.erp.api.fiscal.repository.MensagemFiscalRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ConfigMensagemFiscalMapper extends GenericMapper<ConfigMensagemFiscal, ConfigMensagemFiscalDTO> {

	MensagemFiscalRepository mensagemFiscalRepository;
	
	public ConfigMensagemFiscalMapper(ConfigMensagemFiscalRepository ConfigMensagemFiscalRepository, MensagemFiscalRepository mensagemFiscalRepository) {
		super(ConfigMensagemFiscalRepository, ConfigMensagemFiscal::new, ConfigMensagemFiscalDTO::new);
		this.mensagemFiscalRepository = mensagemFiscalRepository;
    }

	@Override
	protected void setValuesToEntity(ConfigMensagemFiscalDTO dto, ConfigMensagemFiscal entity) {
		entity.setId(dto.getId());
		entity.setDataInicioVigencia(dto.getDataInicioVigencia());
		entity.setDataFinalVigencia(dto.getDataFinalVigencia());
		entity.setMensagemFiscal(mensagemFiscalRepository.findById(dto.getMensagemFiscalId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrada Mensagem Fiscal com id " + dto.getMensagemFiscalId())));
	}

	@Override
	protected void setValuesToDto(ConfigMensagemFiscal entity, ConfigMensagemFiscalDTO dto) {
		dto.setId(entity.getId());
		dto.setDataInicioVigencia(entity.getDataInicioVigencia());
		dto.setDataFinalVigencia(entity.getDataFinalVigencia());
		if (entity.getMensagemFiscal() != null) {
			dto.setMensagemFiscalId(entity.getMensagemFiscal().getId());
			dto.setMensagemFiscalNome(entity.getMensagemFiscal().getNome());
			dto.setMensagemFiscalCodigo(entity.getMensagemFiscal().getCodigo());
		}
	}	
}

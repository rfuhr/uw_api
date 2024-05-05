package br.com.ultraworks.erp.api.fiscal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscaltipoincentfiscal.ConfigMensagemFiscalTipoIncentFiscal;
import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscaltipoincentfiscal.ConfigMensagemFiscalTipoIncentFiscalDTO;
import br.com.ultraworks.erp.api.fiscal.mapper.ConfigMensagemFiscalTipoIncentFiscalMapper;
import br.com.ultraworks.erp.api.fiscal.repository.ConfigMensagemFiscalTipoIncentFiscalRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ConfigMensagemFiscalTipoIncentFiscalService extends GenericService<ConfigMensagemFiscalTipoIncentFiscal, Long, ConfigMensagemFiscalTipoIncentFiscalDTO> {

	ConfigMensagemFiscalTipoIncentFiscalRepository repository;
	
	@Autowired
	public ConfigMensagemFiscalTipoIncentFiscalService(ConfigMensagemFiscalTipoIncentFiscalRepository repository, ConfigMensagemFiscalTipoIncentFiscalMapper mapper) {
		super(repository, mapper);
		this.repository = repository;
	}

	public List<ConfigMensagemFiscalTipoIncentFiscal> getAllByConfigMensagemFiscal(Long id) {
		List<ConfigMensagemFiscalTipoIncentFiscal> listRegistros = new ArrayList<>();

		repository.findByConfigMensagemFiscalId(id).forEach(situacTrib -> {
			listRegistros.add(situacTrib);
		});
		return listRegistros;
	}
	
}
package br.com.ultraworks.erp.api.fiscal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalsituactrib.ConfigMensagemFiscalSituacTrib;
import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalsituactrib.ConfigMensagemFiscalSituacTribDTO;
import br.com.ultraworks.erp.api.fiscal.mapper.ConfigMensagemFiscalSituacTribMapper;
import br.com.ultraworks.erp.api.fiscal.repository.ConfigMensagemFiscalSituacTribRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ConfigMensagemFiscalSituacTribService extends GenericService<ConfigMensagemFiscalSituacTrib, Long, ConfigMensagemFiscalSituacTribDTO> {

	ConfigMensagemFiscalSituacTribRepository repository;
	
	@Autowired
	public ConfigMensagemFiscalSituacTribService(ConfigMensagemFiscalSituacTribRepository repository, ConfigMensagemFiscalSituacTribMapper mapper) {
		super(repository, mapper);
		this.repository = repository;
	}

	public List<ConfigMensagemFiscalSituacTrib> getAllByConfigMensagemFiscal(Long id) {
		List<ConfigMensagemFiscalSituacTrib> listRegistros = new ArrayList<>();

		repository.findByConfigMensagemFiscalId(id).forEach(situacTrib -> {
			listRegistros.add(situacTrib);
		});
		return listRegistros;
	}
	
}
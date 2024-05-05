package br.com.ultraworks.erp.api.fiscal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscaloperinterna.ConfigMensagemFiscalOperInterna;
import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscaloperinterna.ConfigMensagemFiscalOperInternaDTO;
import br.com.ultraworks.erp.api.fiscal.mapper.ConfigMensagemFiscalOperInternaMapper;
import br.com.ultraworks.erp.api.fiscal.repository.ConfigMensagemFiscalOperInternaRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ConfigMensagemFiscalOperInternaService extends GenericService<ConfigMensagemFiscalOperInterna, Long, ConfigMensagemFiscalOperInternaDTO> {

	ConfigMensagemFiscalOperInternaRepository repository;
	
	@Autowired
	public ConfigMensagemFiscalOperInternaService(ConfigMensagemFiscalOperInternaRepository repository, ConfigMensagemFiscalOperInternaMapper mapper) {
		super(repository, mapper);
		this.repository = repository;
	}

	public List<ConfigMensagemFiscalOperInterna> getAllByConfigMensagemFiscal(Long id) {
		List<ConfigMensagemFiscalOperInterna> listRegistros = new ArrayList<>();

		repository.findByConfigMensagemFiscalId(id).forEach(situacTrib -> {
			listRegistros.add(situacTrib);
		});
		return listRegistros;
	}
	
}
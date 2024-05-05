package br.com.ultraworks.erp.api.fiscal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalitem.ConfigMensagemFiscalItem;
import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalitem.ConfigMensagemFiscalItemDTO;
import br.com.ultraworks.erp.api.fiscal.mapper.ConfigMensagemFiscalItemMapper;
import br.com.ultraworks.erp.api.fiscal.repository.ConfigMensagemFiscalItemRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ConfigMensagemFiscalItemService extends GenericService<ConfigMensagemFiscalItem, Long, ConfigMensagemFiscalItemDTO> {

	ConfigMensagemFiscalItemRepository repository;
	
	@Autowired
	public ConfigMensagemFiscalItemService(ConfigMensagemFiscalItemRepository repository, ConfigMensagemFiscalItemMapper mapper) {
		super(repository, mapper);
		this.repository = repository;
	}

	public List<ConfigMensagemFiscalItem> getAllByConfigMensagemFiscal(Long id) {
		List<ConfigMensagemFiscalItem> listRegistros = new ArrayList<>();

		repository.findByConfigMensagemFiscalId(id).forEach(situacTrib -> {
			listRegistros.add(situacTrib);
		});
		return listRegistros;
	}
	
}
package br.com.ultraworks.erp.api.fiscal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalgrupotrib.ConfigMensagemFiscalGrupoTrib;
import br.com.ultraworks.erp.api.fiscal.domain.configmensagemfiscalgrupotrib.ConfigMensagemFiscalGrupoTribDTO;
import br.com.ultraworks.erp.api.fiscal.mapper.ConfigMensagemFiscalGrupoTribMapper;
import br.com.ultraworks.erp.api.fiscal.repository.ConfigMensagemFiscalGrupoTribRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ConfigMensagemFiscalGrupoTribService extends GenericService<ConfigMensagemFiscalGrupoTrib, Long, ConfigMensagemFiscalGrupoTribDTO> {

	ConfigMensagemFiscalGrupoTribRepository repository;
	
	@Autowired
	public ConfigMensagemFiscalGrupoTribService(ConfigMensagemFiscalGrupoTribRepository repository, ConfigMensagemFiscalGrupoTribMapper mapper) {
		super(repository, mapper);
		this.repository = repository;
	}

	public List<ConfigMensagemFiscalGrupoTrib> getAllByConfigMensagemFiscal(Long id) {
		List<ConfigMensagemFiscalGrupoTrib> listRegistros = new ArrayList<>();

		repository.findByConfigMensagemFiscalId(id).forEach(situacTrib -> {
			listRegistros.add(situacTrib);
		});
		return listRegistros;
	}
	
}
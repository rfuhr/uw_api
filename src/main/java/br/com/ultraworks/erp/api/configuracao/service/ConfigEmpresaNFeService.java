package br.com.ultraworks.erp.api.configuracao.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.configuracao.domain.configempresanfe.ConfigEmpresaNFe;
import br.com.ultraworks.erp.api.configuracao.domain.configempresanfe.ConfigEmpresaNFeDTO;
import br.com.ultraworks.erp.api.configuracao.mapper.ConfigEmpresaNFeMapper;
import br.com.ultraworks.erp.api.configuracao.repository.ConfigEmpresaNFeRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ConfigEmpresaNFeService extends GenericService<ConfigEmpresaNFe, Long, ConfigEmpresaNFeDTO> {

	@Autowired
	public ConfigEmpresaNFeService(ConfigEmpresaNFeRepository repository, ConfigEmpresaNFeMapper mapper) {
		super(repository, mapper);
	}

	public List<ConfigEmpresaNFe> getAllByConfigEmpresa(Long id) {
		List<ConfigEmpresaNFe> listRegistros = new ArrayList<>();

		listRegistros.addAll(((ConfigEmpresaNFeRepository) repository).findByConfigEmpresaId(id));
		return listRegistros;
	}

	public ConfigEmpresaNFe getByEmpresaFilialId(Long empresaFilialId) {
		return ((ConfigEmpresaNFeRepository) repository).findByEmpresaFilialId(empresaFilialId);
	}

}
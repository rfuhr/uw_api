package br.com.ultraworks.erp.api.fiscal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.fiscal.domain.configincentivofiscalparceiro.ConfigIncentivoFiscalParceiro;
import br.com.ultraworks.erp.api.fiscal.domain.configincentivofiscalparceiro.ConfigIncentivoFiscalParceiroDTO;
import br.com.ultraworks.erp.api.fiscal.mapper.ConfigIncentivoFiscalParceiroMapper;
import br.com.ultraworks.erp.api.fiscal.repository.ConfigIncentivoFiscalParceiroRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ConfigIncentivoFiscalParceiroService extends GenericService<ConfigIncentivoFiscalParceiro, Long, ConfigIncentivoFiscalParceiroDTO> {

	ConfigIncentivoFiscalParceiroRepository repository;
	
	@Autowired
	public ConfigIncentivoFiscalParceiroService(ConfigIncentivoFiscalParceiroRepository repository, ConfigIncentivoFiscalParceiroMapper mapper) {
		super(repository, mapper);
		this.repository = repository;
	}

	public List<ConfigIncentivoFiscalParceiro> getAllByConfigIncentivoFiscal(Long id) {
		List<ConfigIncentivoFiscalParceiro> listRegistros = new ArrayList<>();

		repository.findByConfigIncentivoFiscalId(id).forEach(configParceiro -> {
			listRegistros.add(configParceiro);
		});
		return listRegistros;
	}
	
}
package br.com.ultraworks.erp.api.configuracao.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.configuracao.domain.configsistemaagricola.ConfigSistemaAgricola;
import br.com.ultraworks.erp.api.configuracao.domain.configsistemaagricola.ConfigSistemaAgricolaDTO;
import br.com.ultraworks.erp.api.configuracao.mapper.ConfigSistemaAgricolaMapper;
import br.com.ultraworks.erp.api.configuracao.repository.ConfigSistemaAgricolaRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import br.com.ultraworks.erp.core.util.ListUtils;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ConfigSistemaAgricolaService
		extends GenericService<ConfigSistemaAgricola, Long, ConfigSistemaAgricolaDTO> {

	@Autowired
	public ConfigSistemaAgricolaService(ConfigSistemaAgricolaRepository repository,
			ConfigSistemaAgricolaMapper mapper) {
		super(repository, mapper);
	}

	public List<ConfigSistemaAgricola> getAllByConfigSistema(Long id) {
		List<ConfigSistemaAgricola> listRegistros = new ArrayList<>();

		listRegistros.addAll(((ConfigSistemaAgricolaRepository) repository).findByConfigSistemaId(id));
		return listRegistros;
	}

	public void persistList(Long configSistemaId, List<ConfigSistemaAgricola> configuracoesAgricola) {
		List<ConfigSistemaAgricola> csfinSalvos = ((ConfigSistemaAgricolaRepository) repository)
				.findByConfigSistemaId(configSistemaId);
		if (configuracoesAgricola != null)
			configuracoesAgricola.stream().forEach(csfin -> {
				repository.save(csfin);
			});
		List<ConfigSistemaAgricola> csfinExcluir = (List<ConfigSistemaAgricola>) ListUtils
				.compararListasERetornaDiferenca(csfinSalvos, configuracoesAgricola);
		csfinExcluir.stream().forEach(oif -> repository.deleteById(oif.getId()));
	}

	public void deleteList(List<ConfigSistemaAgricola> list) {
		if (list != null && !list.isEmpty()) {
			list.stream().forEach(l -> {
				repository.deleteById(l.getId());
			});
		}
	}

}
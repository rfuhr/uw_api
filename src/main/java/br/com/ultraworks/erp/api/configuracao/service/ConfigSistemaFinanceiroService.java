package br.com.ultraworks.erp.api.configuracao.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.configuracao.domain.configsistemafinanceiro.ConfigSistemaFinanceiro;
import br.com.ultraworks.erp.api.configuracao.domain.configsistemafinanceiro.ConfigSistemaFinanceiroDTO;
import br.com.ultraworks.erp.api.configuracao.mapper.ConfigSistemaFinanceiroMapper;
import br.com.ultraworks.erp.api.configuracao.repository.ConfigSistemaFinanceiroRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import br.com.ultraworks.erp.core.util.ListUtils;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ConfigSistemaFinanceiroService
		extends GenericService<ConfigSistemaFinanceiro, Long, ConfigSistemaFinanceiroDTO> {

	@Autowired
	public ConfigSistemaFinanceiroService(ConfigSistemaFinanceiroRepository repository,
			ConfigSistemaFinanceiroMapper mapper) {
		super(repository, mapper);
	}

	public List<ConfigSistemaFinanceiro> getAllByConfigSistema(Long id) {
		List<ConfigSistemaFinanceiro> listRegistros = new ArrayList<>();

		listRegistros.addAll(((ConfigSistemaFinanceiroRepository) repository).findByConfigSistemaId(id));
		return listRegistros;
	}

	public void persistList(Long configSistemaId, List<ConfigSistemaFinanceiro> configuracoesFinanceiro) {
		List<ConfigSistemaFinanceiro> csfinSalvos = ((ConfigSistemaFinanceiroRepository) repository)
				.findByConfigSistemaId(configSistemaId);
		if (configuracoesFinanceiro != null)
			configuracoesFinanceiro.stream().forEach(csfin -> {
				repository.save(csfin);
			});
		List<ConfigSistemaFinanceiro> csfinExcluir = (List<ConfigSistemaFinanceiro>) ListUtils
				.compararListasERetornaDiferenca(csfinSalvos, configuracoesFinanceiro);
		csfinExcluir.stream().forEach(oif -> repository.deleteById(oif.getId()));
	}

	public void deleteList(List<ConfigSistemaFinanceiro> list) {
		if (list != null && !list.isEmpty()) {
			list.stream().forEach(l -> {
				repository.deleteById(l.getId());
			});
		}
	}

}
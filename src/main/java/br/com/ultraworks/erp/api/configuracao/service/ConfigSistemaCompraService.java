package br.com.ultraworks.erp.api.configuracao.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.configuracao.domain.configsistemaagricola.ConfigSistemaAgricola;
import br.com.ultraworks.erp.api.configuracao.domain.configsistemacompra.ConfigSistemaCompra;
import br.com.ultraworks.erp.api.configuracao.domain.configsistemacompra.ConfigSistemaCompraDTO;
import br.com.ultraworks.erp.api.configuracao.mapper.ConfigSistemaCompraMapper;
import br.com.ultraworks.erp.api.configuracao.repository.ConfigSistemaAgricolaRepository;
import br.com.ultraworks.erp.api.configuracao.repository.ConfigSistemaCompraRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import br.com.ultraworks.erp.core.util.ListUtils;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ConfigSistemaCompraService
		extends GenericService<ConfigSistemaCompra, Long, ConfigSistemaCompraDTO> {

	@Autowired
	public ConfigSistemaCompraService(ConfigSistemaCompraRepository repository,
			ConfigSistemaCompraMapper mapper) {
		super(repository, mapper);
	}

	public List<ConfigSistemaCompra> getAllByConfigSistema(Long id) {
		List<ConfigSistemaCompra> listRegistros = new ArrayList<>();

		listRegistros.addAll(((ConfigSistemaCompraRepository) repository).findByConfigSistemaId(id));
		return listRegistros;
	}

	public void persistList(Long configSistemaId, List<ConfigSistemaCompra> configuracoesCompra) {
		List<ConfigSistemaCompra> cscprSalvos = ((ConfigSistemaCompraRepository) repository)
				.findByConfigSistemaId(configSistemaId);
		if (configuracoesCompra != null)
			configuracoesCompra.stream().forEach(cscpr -> {
				repository.save(cscpr);
			});
		List<ConfigSistemaCompra> cscprExcluir = (List<ConfigSistemaCompra>) ListUtils
				.compararListasERetornaDiferenca(cscprSalvos, configuracoesCompra);
		cscprExcluir.stream().forEach(cpr -> repository.deleteById(cpr.getId()));
	}

	public void deleteList(List<ConfigSistemaCompra> list) {
		if (list != null && !list.isEmpty()) {
			list.stream().forEach(cpr -> {
				repository.deleteById(cpr.getId());
			});
		}
	}

}
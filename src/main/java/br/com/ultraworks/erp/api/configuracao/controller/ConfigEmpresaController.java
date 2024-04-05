package br.com.ultraworks.erp.api.configuracao.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.configuracao.domain.configempresa.ConfigEmpresa;
import br.com.ultraworks.erp.api.configuracao.domain.configempresa.ConfigEmpresaDTO;
import br.com.ultraworks.erp.api.configuracao.domain.configempresanfe.ConfigEmpresaNFe;
import br.com.ultraworks.erp.api.configuracao.domain.configempresanfe.ConfigEmpresaNFeDTO;
import br.com.ultraworks.erp.api.configuracao.mapper.ConfigEmpresaMapper;
import br.com.ultraworks.erp.api.configuracao.mapper.ConfigEmpresaNFeMapper;
import br.com.ultraworks.erp.api.configuracao.service.ConfigEmpresaNFeService;
import br.com.ultraworks.erp.api.configuracao.service.ConfigEmpresaService;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/configuracao/empresa")
public class ConfigEmpresaController extends GenericController<ConfigEmpresa, Long, ConfigEmpresaDTO> {

	private ConfigEmpresaNFeService configEmpresaNFeService;
	private ConfigEmpresaNFeMapper configEmpresaNFeMapper;

	public ConfigEmpresaController(ConfigEmpresaService service, ConfigEmpresaMapper mapper, ConfigEmpresaNFeService configEmpresaNFeService,
			ConfigEmpresaNFeMapper configEmpresaNFeMapper) {
		super(service, mapper);
		this.configEmpresaNFeService = configEmpresaNFeService;
		this.configEmpresaNFeMapper = configEmpresaNFeMapper;
	}
	
	@GetMapping("service/get-by-empresa/{empresaId}")
	public ResponseEntity<ConfigEmpresaDTO> getByEmpresaId(@PathVariable Long empresaId) {
		Optional<ConfigEmpresa> entity = ((ConfigEmpresaService) service).getByEmpresaId(empresaId);
		if (entity.isPresent()) {
			return ResponseEntity.ok(mapper.toDto(entity.get()));
		} else {
			throw  new RegisterNotFoundException("Não encontrado registro");
		}
	};
	
	@GetMapping("service/get-config-nfe/{empresaFilialId}")
	public ResponseEntity<ConfigEmpresaNFeDTO> getConfigNFe(@PathVariable Long empresaFilialId) {
		ConfigEmpresaNFe entity = configEmpresaNFeService.getByEmpresaFilialId(empresaFilialId);
		if (entity != null) {
			return ResponseEntity.ok(configEmpresaNFeMapper.toDto(entity));
		} else {
			throw  new RegisterNotFoundException("Não encontrado registro");
		}
	};

}
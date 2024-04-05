package br.com.ultraworks.erp.api.configuracao.mapper;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.configuracao.domain.configempresa.ConfigEmpresa;
import br.com.ultraworks.erp.api.configuracao.domain.configempresa.ConfigEmpresaDTO;
import br.com.ultraworks.erp.api.configuracao.repository.ConfigEmpresaRepository;
import br.com.ultraworks.erp.api.fiscal.service.RegimeTributarioService;
import br.com.ultraworks.erp.api.organograma.service.EmpresaService;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ConfigEmpresaMapper extends GenericMapper<ConfigEmpresa, ConfigEmpresaDTO> {

	private EmpresaService empresaService;
	private ConfigEmpresaNFeMapper configEmpresaNFeMapper;
	private RegimeTributarioService regimeTributarioService;

	public ConfigEmpresaMapper(ConfigEmpresaRepository repository, EmpresaService empresaService,
			ConfigEmpresaNFeMapper configEmpresaNFeMapper, RegimeTributarioService regimeTributarioService) {
		super(repository, ConfigEmpresa::new, ConfigEmpresaDTO::new);
		this.empresaService = empresaService;
		this.configEmpresaNFeMapper = configEmpresaNFeMapper;
		this.regimeTributarioService = regimeTributarioService;
	}

	@Override
	protected void setValuesToEntity(ConfigEmpresaDTO dto, ConfigEmpresa entity) {
		entity.setId(dto.getId());
		entity.setEmpresa(empresaService.getById(dto.getEmpresaId()).orElseThrow(
				() -> new RegisterNotFoundException("Não encontrado empresa com id " + dto.getEmpresaId())));
		if (dto.getConfiguracoesNFe() != null && dto.getConfiguracoesNFe().size() > 0) {
			entity.setConfiguracoesNFe(new ArrayList<>());
			entity.getConfiguracoesNFe().addAll(configEmpresaNFeMapper.toEntity(dto.getConfiguracoesNFe()));
		}
		entity.setRegimeTributario(regimeTributarioService.getById(dto.getRegimeTributarioId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado regime tributário com id " + dto.getRegimeTributarioId())));

	}

	@Override
	protected void setValuesToDto(ConfigEmpresa entity, ConfigEmpresaDTO dto) {
		dto.setId(entity.getId());
		dto.setEmpresaId(entity.getEmpresa().getId());
		dto.setEmpresaNome(entity.getEmpresa().getNome());
		dto.setConfiguracoesNFe(new ArrayList<>());
		if (entity.getConfiguracoesNFe() != null) {
			dto.getConfiguracoesNFe().addAll(configEmpresaNFeMapper.toDto(entity.getConfiguracoesNFe()));
		}
		dto.setRegimeTributarioId(entity.getRegimeTributario().getId());
	}
}

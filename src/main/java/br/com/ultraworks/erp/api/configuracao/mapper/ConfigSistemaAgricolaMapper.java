package br.com.ultraworks.erp.api.configuracao.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.agricola.repository.TipoPrecoAgricolaRepository;
import br.com.ultraworks.erp.api.configuracao.domain.configsistemaagricola.ConfigSistemaAgricola;
import br.com.ultraworks.erp.api.configuracao.domain.configsistemaagricola.ConfigSistemaAgricolaDTO;
import br.com.ultraworks.erp.api.configuracao.repository.ConfigSistemaAgricolaRepository;
import br.com.ultraworks.erp.api.configuracao.repository.ConfigSistemaRepository;
import br.com.ultraworks.erp.api.tabela.repository.OperacaoInternaRepository;
import br.com.ultraworks.erp.api.tabela.repository.TipoDocumentoRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ConfigSistemaAgricolaMapper extends GenericMapper<ConfigSistemaAgricola, ConfigSistemaAgricolaDTO> {

	ConfigSistemaRepository configSistemaRepository;
	TipoDocumentoRepository tipoDocumentoRepository;
	OperacaoInternaRepository operacaoInternaRepository;

	public ConfigSistemaAgricolaMapper(ConfigSistemaAgricolaRepository configSistemaAgricolaRepository,
			ConfigSistemaRepository configSistemaRepository, TipoDocumentoRepository tipoDocumentoRepository,
			OperacaoInternaRepository operacaoInternaRepository) {
		super(configSistemaAgricolaRepository, ConfigSistemaAgricola::new, ConfigSistemaAgricolaDTO::new);
		this.configSistemaRepository = configSistemaRepository;
		this.tipoDocumentoRepository = tipoDocumentoRepository;
		this.operacaoInternaRepository = operacaoInternaRepository;
	}

	@Override
	protected void setValuesToEntity(ConfigSistemaAgricolaDTO dto, ConfigSistemaAgricola entity) {
		entity.setId(dto.getId());
		if (dto.getConfigSistemaId() != null) {
			entity.setConfigSistema(configSistemaRepository.findById(dto.getConfigSistemaId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado configuração de sistema com id " + dto.getConfigSistemaId())));
		}
		entity.setTipoDocumentoRomaneio(tipoDocumentoRepository.findById(dto.getTipoDocumentoRomaneioId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado tipo de documento romaneio com id " + dto.getTipoDocumentoRomaneioId())));
		if (entity.getOperacaoInternaFixacao() != null) {
			entity.setOperacaoInternaFixacao(operacaoInternaRepository.findById(dto.getOperacaoInternaFixacaoId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado operação interna para fixação com id " + dto.getOperacaoInternaFixacaoId())));			
		}
	}

	@Override
	protected void setValuesToDto(ConfigSistemaAgricola entity, ConfigSistemaAgricolaDTO dto) {
		dto.setId(entity.getId());
		dto.setConfigSistemaId(entity.getConfigSistema().getId());
		dto.setTipoDocumentoRomaneioId(entity.getTipoDocumentoRomaneio().getId());
		if (entity.getOperacaoInternaFixacao() != null)
			dto.setOperacaoInternaFixacaoId(entity.getOperacaoInternaFixacao().getId());
	}
}

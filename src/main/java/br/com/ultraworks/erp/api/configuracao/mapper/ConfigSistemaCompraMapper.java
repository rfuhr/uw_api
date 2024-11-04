package br.com.ultraworks.erp.api.configuracao.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.configuracao.domain.configsistemacompra.ConfigSistemaCompra;
import br.com.ultraworks.erp.api.configuracao.domain.configsistemacompra.ConfigSistemaCompraDTO;
import br.com.ultraworks.erp.api.configuracao.repository.ConfigSistemaCompraRepository;
import br.com.ultraworks.erp.api.configuracao.repository.ConfigSistemaRepository;
import br.com.ultraworks.erp.api.tabela.repository.TipoDocumentoRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ConfigSistemaCompraMapper extends GenericMapper<ConfigSistemaCompra, ConfigSistemaCompraDTO> {

	ConfigSistemaRepository configSistemaRepository;
	TipoDocumentoRepository tipoDocumentoRepository;

	public ConfigSistemaCompraMapper(ConfigSistemaCompraRepository configSistemaCompraRepository,
			ConfigSistemaRepository configSistemaRepository, TipoDocumentoRepository tipoDocumentoRepository) {
		super(configSistemaCompraRepository, ConfigSistemaCompra::new, ConfigSistemaCompraDTO::new);
		this.configSistemaRepository = configSistemaRepository;
		this.tipoDocumentoRepository = tipoDocumentoRepository;
	}

	@Override
	protected void setValuesToEntity(ConfigSistemaCompraDTO dto, ConfigSistemaCompra entity) {
		entity.setId(dto.getId());
		if (dto.getConfigSistemaId() != null) {
			entity.setConfigSistema(configSistemaRepository.findById(dto.getConfigSistemaId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado configuração de sistema com id " + dto.getConfigSistemaId())));
		}
		entity.setTipoDocumentoSolicitacao(tipoDocumentoRepository.findById(dto.getTipoDocumentoSolicitacaoId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado tipo de documento solicitação com id " + dto.getTipoDocumentoSolicitacaoId())));
		entity.setTipoDocumentoCotacao(tipoDocumentoRepository.findById(dto.getTipoDocumentoCotacaoId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado tipo de documento cotação com id " + dto.getTipoDocumentoCotacaoId())));		
	}

	@Override
	protected void setValuesToDto(ConfigSistemaCompra entity, ConfigSistemaCompraDTO dto) {
		dto.setId(entity.getId());
		dto.setConfigSistemaId(entity.getConfigSistema().getId());
		dto.setTipoDocumentoSolicitacaoId(entity.getTipoDocumentoSolicitacao().getId());
		if (entity.getTipoDocumentoCotacao() != null)
			dto.setTipoDocumentoCotacaoId(entity.getTipoDocumentoCotacao().getId());
	}
}

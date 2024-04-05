package br.com.ultraworks.erp.api.configuracao.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.configuracao.domain.configempresanfe.ConfigEmpresaNFe;
import br.com.ultraworks.erp.api.configuracao.domain.configempresanfe.ConfigEmpresaNFeDTO;
import br.com.ultraworks.erp.api.configuracao.domain.tipoambiente.TipoAmbiente;
import br.com.ultraworks.erp.api.configuracao.domain.tipocertificado.TipoCertificado;
import br.com.ultraworks.erp.api.configuracao.domain.tipoemissao.TipoEmissao;
import br.com.ultraworks.erp.api.configuracao.repository.ConfigEmpresaNFeRepository;
import br.com.ultraworks.erp.api.configuracao.repository.ConfigEmpresaRepository;
import br.com.ultraworks.erp.api.organograma.repository.EmpresaFilialRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ConfigEmpresaNFeMapper extends GenericMapper<ConfigEmpresaNFe, ConfigEmpresaNFeDTO> {

	ConfigEmpresaRepository configEmpresaRepository;
	EmpresaFilialRepository empresaFilialRepository;
	
	public ConfigEmpresaNFeMapper(ConfigEmpresaNFeRepository configEmpresaNFeRepository, 
			ConfigEmpresaRepository configEmpresaRepository,
			EmpresaFilialRepository empresaFilialRepository) {
		super(configEmpresaNFeRepository, ConfigEmpresaNFe::new, ConfigEmpresaNFeDTO::new);
		this.configEmpresaRepository = configEmpresaRepository;
		this.empresaFilialRepository = empresaFilialRepository;
	}

	@Override
	protected void setValuesToEntity(ConfigEmpresaNFeDTO dto, ConfigEmpresaNFe entity) {
		entity.setId(dto.getId());
		if (dto.getConfigEmpresaId() != null) {
			entity.setConfigEmpresa(configEmpresaRepository.findById(dto.getConfigEmpresaId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado configuração de empresa com id " + dto.getConfigEmpresaId())));
		}
		entity.setEmpresaFilial(empresaFilialRepository.findById(dto.getEmpresaFilialId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado empresa filial com id " + dto.getEmpresaFilialId())));
		entity.setTipoCertificado(TipoCertificado.fromValue(dto.getTipoCertificado()));
		entity.setTipoAmbiente(TipoAmbiente.fromValue(dto.getTipoAmbiente()));
		entity.setTipoEmissao(TipoEmissao.fromValue(dto.getTipoEmissao()));
		entity.setSerie(dto.getSerie());	}

	@Override
	protected void setValuesToDto(ConfigEmpresaNFe entity, ConfigEmpresaNFeDTO dto) {
		dto.setId(entity.getId());
		dto.setConfigEmpresaId(entity.getConfigEmpresa().getId());
		dto.setEmpresaFilialId(entity.getEmpresaFilial().getId());
		dto.setTipoCertificado(entity.getTipoCertificado().getValue());
		dto.setTipoAmbiente(entity.getTipoAmbiente().getValue());
		dto.setTipoEmissao(entity.getTipoEmissao().getValue());
		dto.setSerie(entity.getSerie());
	}
}

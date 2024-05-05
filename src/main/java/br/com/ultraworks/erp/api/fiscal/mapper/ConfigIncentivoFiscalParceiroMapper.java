package br.com.ultraworks.erp.api.fiscal.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.fiscal.domain.configincentivofiscalparceiro.ConfigIncentivoFiscalParceiro;
import br.com.ultraworks.erp.api.fiscal.domain.configincentivofiscalparceiro.ConfigIncentivoFiscalParceiroDTO;
import br.com.ultraworks.erp.api.fiscal.domain.tipotributo.TipoTributo;
import br.com.ultraworks.erp.api.fiscal.repository.ConfigIncentivoFiscalParceiroRepository;
import br.com.ultraworks.erp.api.fiscal.repository.ConfigIncentivoFiscalRepository;
import br.com.ultraworks.erp.api.relacionamento.repository.ParceiroLocalRepository;
import br.com.ultraworks.erp.api.tabela.repository.UfRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ConfigIncentivoFiscalParceiroMapper extends GenericMapper<ConfigIncentivoFiscalParceiro, ConfigIncentivoFiscalParceiroDTO> {

	ConfigIncentivoFiscalRepository configIncentivoFiscalRepository;
	UfRepository ufRepository;
	ParceiroLocalRepository parceiroLocalRepository;
	
	public ConfigIncentivoFiscalParceiroMapper(ConfigIncentivoFiscalParceiroRepository ConfigIncentivoFiscalParceiroRepository, ConfigIncentivoFiscalRepository configIncentivoFiscalRepository,
			UfRepository ufRepository, ParceiroLocalRepository parceiroLocalRepository) {
		super(ConfigIncentivoFiscalParceiroRepository, ConfigIncentivoFiscalParceiro::new, ConfigIncentivoFiscalParceiroDTO::new);
		this.configIncentivoFiscalRepository = configIncentivoFiscalRepository;
		this.ufRepository = ufRepository;
		this.parceiroLocalRepository = parceiroLocalRepository;
    }

	@Override
	protected void setValuesToEntity(ConfigIncentivoFiscalParceiroDTO dto, ConfigIncentivoFiscalParceiro entity) {
		entity.setId(dto.getId());
		entity.setDataInicioVigencia(dto.getDataInicioVigencia());
		entity.setDataFinalVigencia(dto.getDataFinalVigencia());
		if (dto.getConfigIncentivoFiscalId() != null) {
			entity.setConfigIncentivoFiscal(configIncentivoFiscalRepository.findById(dto.getConfigIncentivoFiscalId())
					.orElseThrow(() -> new RegisterNotFoundException("Não encontrado Configuração de Incentivo Fiscal com id " + dto.getConfigIncentivoFiscalId())));			
		}
		entity.setParceiroLocal(parceiroLocalRepository.findById(dto.getParceiroLocalId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado Parceiro Local com id " + dto.getParceiroLocalId())));
		if (dto.getUfId() != null) {
			entity.setUf(ufRepository.findById(dto.getUfId())
					.orElseThrow(() -> new RegisterNotFoundException("Não encontrado UF com id " + dto.getUfId())));
		}
		if (dto.getTipoTributo() != null) {
			entity.setTipoTributo(TipoTributo.fromValue(dto.getTipoTributo()));
		}
		entity.setPercentualAproveitamento(dto.getPercentualAproveitamento());
	}

	@Override
	protected void setValuesToDto(ConfigIncentivoFiscalParceiro entity, ConfigIncentivoFiscalParceiroDTO dto) {
		dto.setId(entity.getId());
		dto.setConfigIncentivoFiscalId(entity.getConfigIncentivoFiscal().getId());
		dto.setParceiroLocalId(entity.getParceiroLocal().getId());
		dto.setParceiroCnpjCpf(entity.getParceiroLocal().getCpfCnpj());
		dto.setParceiroNomeRazaoSocial(entity.getParceiroLocal().getParceiro().getNomeRazaoSocial());
		dto.setDataInicioVigencia(entity.getDataInicioVigencia());
		dto.setDataFinalVigencia(entity.getDataFinalVigencia());
		if (entity.getUf() != null) {
			dto.setUfId(entity.getUf().getId());
			dto.setUfNome(entity.getUf().getNome());
			dto.setUfSigla(entity.getUf().getSigla());
		}
		if (entity.getTipoTributo() != null) {
			dto.setTipoTributo(entity.getTipoTributo().getValue());
		}
		dto.setPercentualAproveitamento(entity.getPercentualAproveitamento());
	}	
}

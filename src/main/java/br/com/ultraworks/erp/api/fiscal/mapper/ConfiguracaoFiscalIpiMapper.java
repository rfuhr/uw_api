package br.com.ultraworks.erp.api.fiscal.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalipi.ConfiguracaoFiscalIpi;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalipi.ConfiguracaoFiscalIpiDTO;
import br.com.ultraworks.erp.api.fiscal.domain.tipocalculo.TipoCalculo;
import br.com.ultraworks.erp.api.fiscal.repository.ConfiguracaoFiscalIpiRepository;
import br.com.ultraworks.erp.api.fiscal.repository.ConfiguracaoFiscalRepository;
import br.com.ultraworks.erp.api.fiscal.repository.EnquadramentoRepository;
import br.com.ultraworks.erp.api.fiscal.repository.SituacaoTributariaRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ConfiguracaoFiscalIpiMapper extends GenericMapper<ConfiguracaoFiscalIpi, ConfiguracaoFiscalIpiDTO> {

	ConfiguracaoFiscalRepository configuracaoFiscalRepository;
	SituacaoTributariaRepository situacaoTributariaRepository;
	EnquadramentoRepository enquadramentoRepository;
	
	SituacaoTributariaMapper situacaoTributariaMapper;
	EnquadramentoMapper enquadramentoMapper;
	
	public ConfiguracaoFiscalIpiMapper(ConfiguracaoFiscalIpiRepository ConfiguracaoFiscalIpiRepository, ConfiguracaoFiscalRepository configuracaoFiscalRepository,
			SituacaoTributariaRepository situacaoTributariaRepository, SituacaoTributariaMapper situacaoTributariaMapper,
			EnquadramentoRepository enquadramentoRepository, EnquadramentoMapper enquadramentoMapper) {
		super(ConfiguracaoFiscalIpiRepository, ConfiguracaoFiscalIpi::new, ConfiguracaoFiscalIpiDTO::new);
		this.configuracaoFiscalRepository = configuracaoFiscalRepository;
		this.situacaoTributariaRepository = situacaoTributariaRepository;
		this.enquadramentoRepository = enquadramentoRepository;
		
		this.situacaoTributariaMapper = situacaoTributariaMapper;
		this.enquadramentoMapper = enquadramentoMapper;
    }

	@Override
	protected void setValuesToEntity(ConfiguracaoFiscalIpiDTO dto, ConfiguracaoFiscalIpi entity) {
		entity.setId(dto.getId());
		if (dto.getConfiguracaoFiscalId() != null) {
			entity.setConfiguracaoFiscal(configuracaoFiscalRepository.findById(dto.getConfiguracaoFiscalId())
					.orElseThrow(() -> new RegisterNotFoundException("Não encontrado Configuração Fiscal com id " + dto.getConfiguracaoFiscalId())));			
		}
		entity.setSituacaoTributaria(situacaoTributariaRepository.findById(dto.getSituacaoTributariaId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado Situação Tributária com id " + dto.getSituacaoTributariaId())));
		if (dto.getEnquadramentoId() != null) {
			entity.setEnquadramento(enquadramentoRepository.findById(dto.getEnquadramentoId())
					.orElseThrow(() -> new RegisterNotFoundException("Não encontrado Enquadramento com id " + dto.getEnquadramentoId())));
		}
		entity.setTipoCalculo(TipoCalculo.fromValue(dto.getTipoCalculo()));
		entity.setAliquota(dto.getAliquota());
	}

	@Override
	protected void setValuesToDto(ConfiguracaoFiscalIpi entity, ConfiguracaoFiscalIpiDTO dto) {
		dto.setId(entity.getId());
		dto.setConfiguracaoFiscalId(entity.getConfiguracaoFiscal().getId());
		dto.setSituacaoTributariaId(entity.getSituacaoTributaria().getId());
		dto.setSituacaoTributariaNome(entity.getSituacaoTributaria().getNome());
		dto.setSituacaoTributariaCodigo(new Long(entity.getSituacaoTributaria().getCodigo()));
		if (entity.getEnquadramento() != null) {
			dto.setEnquadramentoId(entity.getEnquadramento().getId());
			dto.setEnquadramentoNome(entity.getEnquadramento().getNome());
			dto.setEnquadramentoCodigo(new Long(entity.getEnquadramento().getCodigo()));
		}
		dto.setAliquota(entity.getAliquota());
		if (entity.getTipoCalculo() != null) {
			dto.setTipoCalculo(entity.getTipoCalculo().getValue());
			dto.setTipoCalculoName(entity.getTipoCalculo().getName());
		}
	}
	
}

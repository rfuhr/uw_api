package br.com.ultraworks.erp.api.fiscal.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalipi.ConfiguracaoFiscalIpi;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalipi.ConfiguracaoFiscalIpiDTO;
import br.com.ultraworks.erp.api.fiscal.domain.modalidadebasecalculo.ModalidadeBaseCalculo;
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
		entity.setConfiguracaoFiscal(configuracaoFiscalRepository.findById(dto.getConfiguracaoFiscalId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado Configuração Fiscal com id " + dto.getConfiguracaoFiscalId())));
		entity.setSituacaoTributaria(situacaoTributariaRepository.findById(dto.getSituacaoTributaria().getId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado Situação Tributária com id " + dto.getSituacaoTributaria().getId())));
		entity.setModalidadeBaseCalculo(ModalidadeBaseCalculo.fromCodigo(dto.getModalidadeBaseCalculo()));
		entity.setEnquadramento(enquadramentoRepository.findById(dto.getEnquadramento().getId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado Enquadramento com id " + dto.getEnquadramento().getId())));
		entity.setTipoCalculo(TipoCalculo.fromCodigo(dto.getTipoCalculo()));
		entity.setAliquota(dto.getAliquota());
		entity.setCodigoSelo(dto.getCodigoSelo());
		entity.setQuantidadeSelo(dto.getQuantidadeSelo());
	}

	@Override
	protected void setValuesToDto(ConfiguracaoFiscalIpi entity, ConfiguracaoFiscalIpiDTO dto) {
		dto.setId(entity.getId());
		dto.setConfiguracaoFiscalId(entity.getConfiguracaoFiscal().getId());
		dto.setSituacaoTributaria(situacaoTributariaMapper.toDto(entity.getSituacaoTributaria()));
		dto.setModalidadeBaseCalculo(entity.getModalidadeBaseCalculo().getValue());
		dto.setEnquadramento(enquadramentoMapper.toDto(entity.getEnquadramento()));
		dto.setAliquota(dto.getAliquota());
		dto.setTipoCalculo(entity.getTipoCalculo().getValue());
		dto.setCodigoSelo(entity.getCodigoSelo());
		dto.setQuantidadeSelo(entity.getQuantidadeSelo());
	}
	
}
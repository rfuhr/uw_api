package br.com.ultraworks.erp.api.fiscal.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalpis.ConfiguracaoFiscalPis;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalpis.ConfiguracaoFiscalPisDTO;
import br.com.ultraworks.erp.api.fiscal.domain.modalidadebasecalculo.ModalidadeBaseCalculo;
import br.com.ultraworks.erp.api.fiscal.domain.tipocalculo.TipoCalculo;
import br.com.ultraworks.erp.api.fiscal.repository.ConfiguracaoFiscalPisRepository;
import br.com.ultraworks.erp.api.fiscal.repository.ConfiguracaoFiscalRepository;
import br.com.ultraworks.erp.api.fiscal.repository.SituacaoTributariaRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ConfiguracaoFiscalPisMapper extends GenericMapper<ConfiguracaoFiscalPis, ConfiguracaoFiscalPisDTO> {

	ConfiguracaoFiscalRepository configuracaoFiscalRepository;
	SituacaoTributariaRepository situacaoTributariaRepository;
	
	SituacaoTributariaMapper situacaoTributariaMapper;
	
	public ConfiguracaoFiscalPisMapper(ConfiguracaoFiscalPisRepository ConfiguracaoFiscalPisRepository, ConfiguracaoFiscalRepository configuracaoFiscalRepository,
			SituacaoTributariaRepository situacaoTributariaRepository, SituacaoTributariaMapper situacaoTributariaMapper) {
		super(ConfiguracaoFiscalPisRepository, ConfiguracaoFiscalPis::new, ConfiguracaoFiscalPisDTO::new);
		this.configuracaoFiscalRepository = configuracaoFiscalRepository;
		this.situacaoTributariaRepository = situacaoTributariaRepository;
		
		this.situacaoTributariaMapper = situacaoTributariaMapper;
    }

	@Override
	protected void setValuesToEntity(ConfiguracaoFiscalPisDTO dto, ConfiguracaoFiscalPis entity) {
		entity.setId(dto.getId());
		entity.setConfiguracaoFiscal(configuracaoFiscalRepository.findById(dto.getConfiguracaoFiscalId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado Configuração Fiscal com id " + dto.getConfiguracaoFiscalId())));
		entity.setSituacaoTributaria(situacaoTributariaRepository.findById(dto.getSituacaoTributaria().getId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado Situação Tributária com id " + dto.getSituacaoTributaria().getId())));
		entity.setModalidadeBaseCalculo(ModalidadeBaseCalculo.fromValue(dto.getModalidadeBaseCalculo()));
		entity.setTipoCalculo(TipoCalculo.fromValue(dto.getTipoCalculo()));
		entity.setAliquota(dto.getAliquota());
		entity.setTipoCalculoST(TipoCalculo.fromValue(dto.getTipoCalculoST()));
		entity.setAliquotaST(dto.getAliquotaST());
	}

	@Override
	protected void setValuesToDto(ConfiguracaoFiscalPis entity, ConfiguracaoFiscalPisDTO dto) {
		dto.setId(entity.getId());
		dto.setConfiguracaoFiscalId(entity.getConfiguracaoFiscal().getId());
		dto.setSituacaoTributariaId(entity.getSituacaoTributaria().getId());
		dto.setSituacaoTributariaNome(entity.getSituacaoTributaria().getNome());
		dto.setSituacaoTributariaCodigo(new Long(entity.getSituacaoTributaria().getCodigo()));
		dto.setModalidadeBaseCalculo(entity.getModalidadeBaseCalculo().getValue());
		dto.setAliquota(dto.getAliquota());
		dto.setTipoCalculo(entity.getTipoCalculo().getValue());
		dto.setAliquotaST(dto.getAliquotaST());
		dto.setTipoCalculoST(entity.getTipoCalculoST().getValue());
	}
	
}

package br.com.ultraworks.erp.api.fiscal.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalcofins.ConfiguracaoFiscalCofins;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalcofins.ConfiguracaoFiscalCofinsDTO;
import br.com.ultraworks.erp.api.fiscal.domain.modalidadebasecalculo.ModalidadeBaseCalculo;
import br.com.ultraworks.erp.api.fiscal.domain.tipocalculo.TipoCalculo;
import br.com.ultraworks.erp.api.fiscal.repository.ConfiguracaoFiscalCofinsRepository;
import br.com.ultraworks.erp.api.fiscal.repository.ConfiguracaoFiscalRepository;
import br.com.ultraworks.erp.api.fiscal.repository.SituacaoTributariaRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ConfiguracaoFiscalCofinsMapper extends GenericMapper<ConfiguracaoFiscalCofins, ConfiguracaoFiscalCofinsDTO> {

	ConfiguracaoFiscalRepository configuracaoFiscalRepository;
	SituacaoTributariaRepository situacaoTributariaRepository;
	
	SituacaoTributariaMapper situacaoTributariaMapper;
	
	public ConfiguracaoFiscalCofinsMapper(ConfiguracaoFiscalCofinsRepository ConfiguracaoFiscalCofinsRepository, ConfiguracaoFiscalRepository configuracaoFiscalRepository,
			SituacaoTributariaRepository situacaoTributariaRepository, SituacaoTributariaMapper situacaoTributariaMapper) {
		super(ConfiguracaoFiscalCofinsRepository, ConfiguracaoFiscalCofins::new, ConfiguracaoFiscalCofinsDTO::new);
		this.configuracaoFiscalRepository = configuracaoFiscalRepository;
		this.situacaoTributariaRepository = situacaoTributariaRepository;
		
		this.situacaoTributariaMapper = situacaoTributariaMapper;
    }

	@Override
	protected void setValuesToEntity(ConfiguracaoFiscalCofinsDTO dto, ConfiguracaoFiscalCofins entity) {
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
	protected void setValuesToDto(ConfiguracaoFiscalCofins entity, ConfiguracaoFiscalCofinsDTO dto) {
		dto.setId(entity.getId());
		dto.setConfiguracaoFiscalId(entity.getConfiguracaoFiscal().getId());
		dto.setSituacaoTributaria(situacaoTributariaMapper.toDto(entity.getSituacaoTributaria()));
		dto.setModalidadeBaseCalculo(entity.getModalidadeBaseCalculo().getValue());
		dto.setAliquota(dto.getAliquota());
		dto.setTipoCalculo(entity.getTipoCalculo().getValue());
		dto.setAliquotaST(dto.getAliquotaST());
		dto.setTipoCalculoST(entity.getTipoCalculoST().getValue());
	}
	
}
package br.com.ultraworks.erp.api.fiscal.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalicms.ConfiguracaoFiscalIcms;
import br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscalicms.ConfiguracaoFiscalIcmsDTO;
import br.com.ultraworks.erp.api.fiscal.domain.modalidadebasecalculo.ModalidadeBaseCalculo;
import br.com.ultraworks.erp.api.fiscal.repository.ConfiguracaoFiscalIcmsRepository;
import br.com.ultraworks.erp.api.fiscal.repository.ConfiguracaoFiscalRepository;
import br.com.ultraworks.erp.api.fiscal.repository.MotivoDesoneracaoRepository;
import br.com.ultraworks.erp.api.fiscal.repository.SituacaoTributariaRepository;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class ConfiguracaoFiscalIcmsMapper extends GenericMapper<ConfiguracaoFiscalIcms, ConfiguracaoFiscalIcmsDTO> {

	ConfiguracaoFiscalRepository configuracaoFiscalRepository;
	SituacaoTributariaRepository situacaoTributariaRepository;
	MotivoDesoneracaoRepository motivoDesoneracaoRepository;
	
	SituacaoTributariaMapper situacaoTributariaMapper;
	MotivoDesoneracaoMapper motivoDesoneracaoMapper;
	
	public ConfiguracaoFiscalIcmsMapper(ConfiguracaoFiscalIcmsRepository ConfiguracaoFiscalIcmsRepository, ConfiguracaoFiscalRepository configuracaoFiscalRepository,
			SituacaoTributariaRepository situacaoTributariaRepository, SituacaoTributariaMapper situacaoTributariaMapper,
			MotivoDesoneracaoRepository motivoDesoneracaoRepository, MotivoDesoneracaoMapper motivoDesoneracaoMapper) {
		super(ConfiguracaoFiscalIcmsRepository, ConfiguracaoFiscalIcms::new, ConfiguracaoFiscalIcmsDTO::new);
		this.configuracaoFiscalRepository = configuracaoFiscalRepository;
		this.situacaoTributariaRepository = situacaoTributariaRepository;
		this.motivoDesoneracaoRepository = motivoDesoneracaoRepository;
		
		this.situacaoTributariaMapper = situacaoTributariaMapper;
		this.motivoDesoneracaoMapper = motivoDesoneracaoMapper;
    }

	@Override
	protected void setValuesToEntity(ConfiguracaoFiscalIcmsDTO dto, ConfiguracaoFiscalIcms entity) {
		entity.setId(dto.getId());
		entity.setConfiguracaoFiscal(configuracaoFiscalRepository.findById(dto.getConfiguracaoFiscalId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado Configuração Fiscal com id " + dto.getConfiguracaoFiscalId())));
		entity.setSituacaoTributaria(situacaoTributariaRepository.findById(dto.getSituacaoTributaria().getId())
				.orElseThrow(() -> new RegisterNotFoundException("Não encontrado Situação Tributária com id " + dto.getSituacaoTributaria().getId())));
		entity.setModalidadeBaseCalculo(ModalidadeBaseCalculo.fromCodigo(dto.getModalidadeBaseCalculo()));
		if (dto.getMotivoDesoneracao() != null) {
			entity.setMotivoDesoneracao(motivoDesoneracaoRepository.findById(dto.getMotivoDesoneracao().getId())
					.orElseThrow(() -> new RegisterNotFoundException("Não encontrado Motivo Desoneração com id " + dto.getMotivoDesoneracao().getId())));
		}
		entity.setReducaoBaseCalculo(dto.getReducaoBaseCalculo());
		entity.setSomaIpiBaseCalculo(dto.isSomaIpiBaseCalculo());
		entity.setAliquota(dto.getAliquota());
		entity.setAliquotaCredito(dto.getAliquotaCredito());
		entity.setDiferencialAliquota(dto.getDiferencialAliquota());
		if (dto.getModalidadeBaseCalculoST() != null) {
			entity.setModalidadeBaseCalculoST(ModalidadeBaseCalculo.fromCodigo(dto.getModalidadeBaseCalculoST()));
		}
		if (dto.getMotivoDesoneracaoST() != null) {
			entity.setMotivoDesoneracaoST(motivoDesoneracaoRepository.findById(dto.getMotivoDesoneracaoST().getId())
					.orElseThrow(() -> new RegisterNotFoundException("Não encontrado Motivo Desoneração ST com id " + dto.getMotivoDesoneracaoST().getId())));
		}
		entity.setReducaoBaseCalculoST(dto.getReducaoBaseCalculoST());
		entity.setSomaIpiBaseCalculoST(dto.isSomaIpiBaseCalculoST());
		entity.setAliquotaST(dto.getAliquotaST());
		entity.setMargemValorAgregadoST(dto.getMargemValorAgregadoST());
	}

	@Override
	protected void setValuesToDto(ConfiguracaoFiscalIcms entity, ConfiguracaoFiscalIcmsDTO dto) {
		dto.setId(entity.getId());
		dto.setConfiguracaoFiscalId(entity.getConfiguracaoFiscal().getId());
		dto.setSituacaoTributaria(situacaoTributariaMapper.toDto(entity.getSituacaoTributaria()));
		if (entity.getModalidadeBaseCalculo() != null) {
			dto.setModalidadeBaseCalculo(entity.getModalidadeBaseCalculo().getValue());
		}
		if (entity.getMotivoDesoneracao() != null) {
			dto.setMotivoDesoneracao(motivoDesoneracaoMapper.toDto(entity.getMotivoDesoneracao()));
		}
		dto.setReducaoBaseCalculo(dto.getReducaoBaseCalculo());
		dto.setSomaIpiBaseCalculo(dto.isSomaIpiBaseCalculo());
		dto.setAliquota(dto.getAliquota());
		dto.setAliquotaCredito(dto.getAliquotaCredito());
		dto.setDiferencialAliquota(dto.getDiferencialAliquota());
		if (entity.getModalidadeBaseCalculoST() != null) {
			dto.setModalidadeBaseCalculoST(entity.getModalidadeBaseCalculoST().getValue());
		}
		if (entity.getMotivoDesoneracaoST() != null) {
			dto.setMotivoDesoneracaoST(motivoDesoneracaoMapper.toDto(entity.getMotivoDesoneracaoST()));
		}
		dto.setReducaoBaseCalculoST(entity.getReducaoBaseCalculoST());
		dto.setSomaIpiBaseCalculoST(entity.isSomaIpiBaseCalculoST());
		dto.setAliquotaST(entity.getAliquotaST());
		dto.setMargemValorAgregadoST(entity.getMargemValorAgregadoST());
	}
	
}
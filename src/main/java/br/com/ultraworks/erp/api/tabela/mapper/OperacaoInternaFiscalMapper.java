package br.com.ultraworks.erp.api.tabela.mapper;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.fiscal.domain.destinooperacao.DestinoOperacao;
import br.com.ultraworks.erp.api.fiscal.domain.finalidadenfe.FinalidadeNfe;
import br.com.ultraworks.erp.api.fiscal.domain.tipoconsumidor.TipoConsumidor;
import br.com.ultraworks.erp.api.fiscal.domain.tipopresencacomprador.TipoPresencaComprador;
import br.com.ultraworks.erp.api.tabela.domain.operacaointernafiscal.OperacaoInternaFiscal;
import br.com.ultraworks.erp.api.tabela.domain.operacaointernafiscal.OperacaoInternaFiscalDTO;
import br.com.ultraworks.erp.api.tabela.repository.OperacaoInternaFiscalRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class OperacaoInternaFiscalMapper extends GenericMapper<OperacaoInternaFiscal, OperacaoInternaFiscalDTO> {

	private OperacaoInternaFiscalCfopMapper operacaoInternaFiscalCfopMapper;

	public OperacaoInternaFiscalMapper(OperacaoInternaFiscalRepository repository,
			OperacaoInternaFiscalCfopMapper operacaoInternaFiscalCfopMapper) {
		super(repository, OperacaoInternaFiscal::new, OperacaoInternaFiscalDTO::new);
		this.operacaoInternaFiscalCfopMapper = operacaoInternaFiscalCfopMapper;
	}

	@Override
	protected void setValuesToEntity(OperacaoInternaFiscalDTO dto, OperacaoInternaFiscal entity) {
		entity.setId(dto.getId());
		if (StringUtils.isNotBlank(dto.getDestinoOperacao()))
			entity.setDestinoOperacao(DestinoOperacao.fromValue(dto.getDestinoOperacao()));
		if (StringUtils.isNotBlank(dto.getFinalidadeNfe()))
			entity.setFinalidadeNfe(FinalidadeNfe.fromValue(dto.getFinalidadeNfe()));
		if (StringUtils.isNotBlank(dto.getTipoConsumidor()))
			entity.setTipoConsumidor(TipoConsumidor.fromValue(dto.getTipoConsumidor()));
		if (StringUtils.isNotBlank(dto.getTipoPresencaComprador()))
			entity.setTipoPresencaComprador(TipoPresencaComprador.fromValue(dto.getTipoPresencaComprador()));
		if (dto.getCfops() != null && dto.getCfops().size() > 0) {
			entity.setCfops(new ArrayList<>());
			entity.getCfops().addAll(operacaoInternaFiscalCfopMapper.toEntity(dto.getCfops()));
		}
	}

	@Override
	protected void setValuesToDto(OperacaoInternaFiscal entity, OperacaoInternaFiscalDTO dto) {
		dto.setId(entity.getId());
		if (entity.getDestinoOperacao() != null)
			dto.setDestinoOperacao(entity.getDestinoOperacao().getValue());
		if (entity.getFinalidadeNfe() != null)
			dto.setFinalidadeNfe(entity.getFinalidadeNfe().getValue());
		if (entity.getTipoConsumidor() != null)
			dto.setTipoConsumidor(entity.getTipoConsumidor().getValue());
		if (entity.getTipoPresencaComprador() != null)
			dto.setTipoPresencaComprador(entity.getTipoPresencaComprador().getValue());
		dto.setCfops(new ArrayList<>());
		if (entity.getCfops() != null) {
			dto.getCfops().addAll(operacaoInternaFiscalCfopMapper.toDto(entity.getCfops()));
		}
	}
}

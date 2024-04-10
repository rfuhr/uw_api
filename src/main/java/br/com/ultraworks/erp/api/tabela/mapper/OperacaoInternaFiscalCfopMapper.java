package br.com.ultraworks.erp.api.tabela.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.fiscal.service.CfopService;
import br.com.ultraworks.erp.api.tabela.domain.operacaointernafiscalcfop.OperacaoInternaFiscalCfop;
import br.com.ultraworks.erp.api.tabela.domain.operacaointernafiscalcfop.OperacaoInternaFiscalCfopDTO;
import br.com.ultraworks.erp.api.tabela.repository.OperacaoInternaFiscalCfopRepository;
import br.com.ultraworks.erp.api.tabela.repository.OperacaoInternaFiscalRepository;
import br.com.ultraworks.erp.api.tabela.service.OperacaoInternaFiscalService;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class OperacaoInternaFiscalCfopMapper
		extends GenericMapper<OperacaoInternaFiscalCfop, OperacaoInternaFiscalCfopDTO> {

	private OperacaoInternaFiscalRepository operacaoInternaFiscalRepository;
	private CfopService cfopService;

	public OperacaoInternaFiscalCfopMapper(OperacaoInternaFiscalCfopRepository repository,
			OperacaoInternaFiscalRepository operacaoInternaFiscalRepository, CfopService cfopService) {
		super(repository, OperacaoInternaFiscalCfop::new, OperacaoInternaFiscalCfopDTO::new);
		this.operacaoInternaFiscalRepository = operacaoInternaFiscalRepository;
		this.cfopService = cfopService;
	}

	@Override
	protected void setValuesToEntity(OperacaoInternaFiscalCfopDTO dto, OperacaoInternaFiscalCfop entity) {
		entity.setId(dto.getId());
		if (dto.getOperacaoInternaFiscalId() != null) {
			entity.setOperacaoInternaFiscal(operacaoInternaFiscalRepository.findById(dto.getOperacaoInternaFiscalId())
					.orElseThrow(() -> new RegisterNotFoundException(
							"Não encontrado configuração fiscal para operação interna com id "
									+ dto.getOperacaoInternaFiscalId())));
		}
		if (dto.getCfopId() != null) {
			entity.setCfop(cfopService.getById(dto.getCfopId())
					.orElseThrow(() -> new RegisterNotFoundException("Não encontrado cfop com id " + dto.getCfopId())));
		}
	}

	@Override
	protected void setValuesToDto(OperacaoInternaFiscalCfop entity, OperacaoInternaFiscalCfopDTO dto) {
		dto.setId(entity.getId());
		if (entity.getOperacaoInternaFiscal() != null)
			dto.setOperacaoInternaFiscalId(entity.getOperacaoInternaFiscal().getId());
		if (entity.getCfop() != null)
			dto.setCfopId(entity.getCfop().getId());
	}
}

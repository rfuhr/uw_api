package br.com.ultraworks.erp.api.tabela.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.tabela.domain.operacaointernafiscal.OperacaoInternaFiscal;
import br.com.ultraworks.erp.api.tabela.domain.operacaointernafiscal.OperacaoInternaFiscalDTO;
import br.com.ultraworks.erp.api.tabela.repository.OperacaoInternaFiscalRepository;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class OperacaoInternaFiscalMapper extends GenericMapper<OperacaoInternaFiscal, OperacaoInternaFiscalDTO> {

	public OperacaoInternaFiscalMapper(OperacaoInternaFiscalRepository repository) {
		super(repository, OperacaoInternaFiscal::new, OperacaoInternaFiscalDTO::new);
	}

	@Override
	protected void setValuesToEntity(OperacaoInternaFiscalDTO dto, OperacaoInternaFiscal entity) {
		entity.setId(dto.getId());
	}

	@Override
	protected void setValuesToDto(OperacaoInternaFiscal entity, OperacaoInternaFiscalDTO dto) {
		dto.setId(entity.getId());
	}
}

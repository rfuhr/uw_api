package br.com.ultraworks.erp.api.financeiro.mapper;

import org.springframework.stereotype.Component;

import br.com.ultraworks.erp.api.financeiro.domain.saldocaixabanco.SaldoCaixaBanco;
import br.com.ultraworks.erp.api.financeiro.domain.saldocaixabanco.SaldoCaixaBancoDTO;
import br.com.ultraworks.erp.api.financeiro.repository.SaldoCaixaBancoRepository;
import br.com.ultraworks.erp.api.financeiro.service.ContaService;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.mapper.GenericMapper;

@Component
public class SaldoCaixaBancoMapper extends GenericMapper<SaldoCaixaBanco, SaldoCaixaBancoDTO> {

	ContaService contaService;
	
	public SaldoCaixaBancoMapper(SaldoCaixaBancoRepository repository,
			ContaService contaService) {
		super(repository, SaldoCaixaBanco::new, SaldoCaixaBancoDTO::new);
		this.contaService = contaService;
	}

	@Override
	protected void setValuesToEntity(SaldoCaixaBancoDTO dto, SaldoCaixaBanco entity) {
		entity.setId(dto.getId());
		entity.setData(dto.getData());
		entity.setSaldoValor(dto.getSaldoValor());
		entity.setConta(contaService.getById(dto.getContaId())
				.orElseThrow(() -> new RegisterNotFoundException(
						"Não encontrado a conta com id " + dto.getContaId())));
	}

	@Override
	protected void setValuesToDto(SaldoCaixaBanco entity, SaldoCaixaBancoDTO dto) {
		dto.setId(entity.getId());
		dto.setData(entity.getData());
		dto.setSaldoValor(entity.getSaldoValor());
		dto.setContaId(entity.getConta().getId());
	}
}

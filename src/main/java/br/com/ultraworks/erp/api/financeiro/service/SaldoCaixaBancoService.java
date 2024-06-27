package br.com.ultraworks.erp.api.financeiro.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ultraworks.erp.api.financeiro.domain.saldocaixabanco.SaldoCaixaBanco;
import br.com.ultraworks.erp.api.financeiro.domain.saldocaixabanco.SaldoCaixaBancoDTO;
import br.com.ultraworks.erp.api.financeiro.domain.saldocaixabanco.SaldoCaixaBancoRequest;
import br.com.ultraworks.erp.api.financeiro.domain.saldocaixabanco.SaldoCaixaBancoResponse;
import br.com.ultraworks.erp.api.financeiro.mapper.SaldoCaixaBancoMapper;
import br.com.ultraworks.erp.api.financeiro.repository.SaldoCaixaBancoRepository;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class SaldoCaixaBancoService extends GenericService<SaldoCaixaBanco, Long, SaldoCaixaBancoDTO> {

	SaldoCaixaBancoRepository repository;
	
	@Autowired
	public SaldoCaixaBancoService(SaldoCaixaBancoRepository repository, SaldoCaixaBancoMapper mapper) {
		super(repository, mapper);
		this.repository = repository;
	}
	
	@Transactional
	public void atualizaSaldoCaixaBanco(Date dataInicio, Date dataFim, Long contaId, Long userId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		repository.atualizaSaldoCaixaBanco(sdf.format(dataInicio), sdf.format(dataFim), contaId, userId);
	}
	
	public List<SaldoCaixaBancoResponse> buscaSaldoCaixaBanco(SaldoCaixaBancoRequest saldoCaixaBancoRequest) {
		String dataInicio = saldoCaixaBancoRequest.getDataInicio().toString();
		String dataFinal = saldoCaixaBancoRequest.getDataFinal().toString();
		int validaBanco = saldoCaixaBancoRequest.getBancoId() == null ? 1 : 0;
		int validaAgencia = saldoCaixaBancoRequest.getAgenciaId() == null ? 1 : 0;
		int validaConta = saldoCaixaBancoRequest.getContaId() == null ? 1 : 0;
		
		return repository.buscaSaldoCaixaBancoResponse(dataInicio, dataFinal, validaBanco, saldoCaixaBancoRequest.getBancoId(), 
				validaAgencia, saldoCaixaBancoRequest.getAgenciaId(), 
				validaConta, saldoCaixaBancoRequest.getContaId());
	}
}
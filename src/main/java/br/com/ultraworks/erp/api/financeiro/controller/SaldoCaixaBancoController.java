package br.com.ultraworks.erp.api.financeiro.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.financeiro.domain.saldocaixabanco.SaldoCaixaBanco;
import br.com.ultraworks.erp.api.financeiro.domain.saldocaixabanco.SaldoCaixaBancoDTO;
import br.com.ultraworks.erp.api.financeiro.domain.saldocaixabanco.SaldoCaixaBancoRequest;
import br.com.ultraworks.erp.api.financeiro.domain.saldocaixabanco.SaldoCaixaBancoResponse;
import br.com.ultraworks.erp.api.financeiro.mapper.SaldoCaixaBancoMapper;
import br.com.ultraworks.erp.api.financeiro.service.SaldoCaixaBancoService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/financeiro/saldo-caixa-banco")
public class SaldoCaixaBancoController extends GenericController<SaldoCaixaBanco, Long, SaldoCaixaBancoDTO> {

	SaldoCaixaBancoService service;
	
	public SaldoCaixaBancoController(SaldoCaixaBancoService service, SaldoCaixaBancoMapper mapper) {
		super(service, mapper);
		this.service = service;
	}
	
	@PostMapping("/consultar")
	public List<SaldoCaixaBancoResponse> buscaSaldoCaixaBanco(@RequestBody SaldoCaixaBancoRequest saldoCaixaBancoRequest) {
		return service.buscaSaldoCaixaBanco(saldoCaixaBancoRequest);
	}	

}
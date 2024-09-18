package br.com.ultraworks.erp.api.financeiro.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.financeiro.domain.operacaocxbco.OperacaoCaixaBanco;
import br.com.ultraworks.erp.api.financeiro.domain.operacaocxbco.OperacaoCaixaBancoDTO;
import br.com.ultraworks.erp.api.financeiro.mapper.OperacaoCaixaBancoMapper;
import br.com.ultraworks.erp.api.financeiro.service.CarteiraFinanceiraService;
import br.com.ultraworks.erp.api.financeiro.service.OperacaoCaixaBancoService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/financeiro/operacao-caixa-banco")
public class OperacaoCaixaBancoController
		extends GenericController<OperacaoCaixaBanco, Long, OperacaoCaixaBancoDTO> {

	public OperacaoCaixaBancoController(OperacaoCaixaBancoService service,
			OperacaoCaixaBancoMapper mapper) {
		super(service, mapper);
	}
	
	@GetMapping("proximo-codigo")
	public ResponseEntity<?> getProximoCodigo() {
		return ResponseEntity.ok(((OperacaoCaixaBancoService) service).getProximoCodigo());
	}	

}
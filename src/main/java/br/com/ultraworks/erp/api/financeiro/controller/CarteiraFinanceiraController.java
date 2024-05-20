package br.com.ultraworks.erp.api.financeiro.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.financeiro.domain.carteirafinanceira.CarteiraFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.carteirafinanceira.CarteiraFinanceiraDTO;
import br.com.ultraworks.erp.api.financeiro.mapper.CarteiraFinanceiraMapper;
import br.com.ultraworks.erp.api.financeiro.service.CarteiraFinanceiraService;
import br.com.ultraworks.erp.api.financeiro.service.TipoTituloService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/financeiro/carteira-financeira")
public class CarteiraFinanceiraController extends GenericController<CarteiraFinanceira, Long, CarteiraFinanceiraDTO> {

	public CarteiraFinanceiraController(CarteiraFinanceiraService service, CarteiraFinanceiraMapper mapper) {
		super(service, mapper);
	}

	@GetMapping("proximo-codigo")
	public ResponseEntity<?> getProximoCodigo() {
		return ResponseEntity.ok(((CarteiraFinanceiraService) service).getProximoCodigo());
	}	
}
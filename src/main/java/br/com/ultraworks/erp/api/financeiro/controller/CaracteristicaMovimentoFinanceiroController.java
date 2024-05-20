package br.com.ultraworks.erp.api.financeiro.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.financeiro.domain.caracteristicamovfin.CaracteristicaMovimentoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.caracteristicamovfin.CaracteristicaMovimentoFinanceiroDTO;
import br.com.ultraworks.erp.api.financeiro.mapper.CaracteristicaMovimentoFinanceiroMapper;
import br.com.ultraworks.erp.api.financeiro.service.CaracteristicaMovimentoFinanceiroService;
import br.com.ultraworks.erp.api.financeiro.service.TipoTituloService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/financeiro/caracteristica-movimento-financeiro")
public class CaracteristicaMovimentoFinanceiroController
		extends GenericController<CaracteristicaMovimentoFinanceiro, Long, CaracteristicaMovimentoFinanceiroDTO> {

	public CaracteristicaMovimentoFinanceiroController(CaracteristicaMovimentoFinanceiroService service,
			CaracteristicaMovimentoFinanceiroMapper mapper) {
		super(service, mapper);
	}

	@GetMapping("proximo-codigo")
	public ResponseEntity<?> getProximoCodigo() {
		return ResponseEntity.ok(((CaracteristicaMovimentoFinanceiroService) service).getProximoCodigo());
	}	
}
package br.com.ultraworks.erp.api.agricola.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.agricola.domain.classificacaoagricola.container.ContainerCalculadoraCalculoAgricola;
import br.com.ultraworks.erp.api.agricola.service.CalculadoraDescontoAgricolaService;

@RestController
@RequestMapping("/agricola/classificacao-agricola")
public class ClassificacaoAgricolaController {

	@Autowired
	private CalculadoraDescontoAgricolaService calculadoraDescontoAgricolaService;

	@PostMapping("servicos/calcular")
	public ResponseEntity<ContainerCalculadoraCalculoAgricola> calcularDescontoAgricola(
			@RequestBody ContainerCalculadoraCalculoAgricola request) {
		calculadoraDescontoAgricolaService.calcularDescontoAgricolaNoRomaneio(request);
		return ResponseEntity.ok(request);
	}
}

package br.com.ultraworks.erp.api.financeiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.financeiro.domain.relatorio.request.SelecaoRelatorioFinanceiroRequest;
import br.com.ultraworks.erp.api.financeiro.report.RelatoriosFinanceiroService;
import br.com.ultraworks.erp.core.dto.ReportGenerated;

@RestController
@RequestMapping("/financeiro/relatorio")
public class RelatorioFinanceiroController {

	@Autowired
	RelatoriosFinanceiroService relatoriosFinanceiroService;
	
	@PostMapping("/imprimir")
	public ResponseEntity<byte[]> imprimirRelatorio(@RequestBody SelecaoRelatorioFinanceiroRequest selecaoRelatorioFinanceiroRequest) {
		ReportGenerated reportGenerated = relatoriosFinanceiroService.imprimirRelatorioFinanceiro(selecaoRelatorioFinanceiroRequest);
		if (reportGenerated != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + reportGenerated.getNameReport());
            return new ResponseEntity<>(reportGenerated.getBytes(), headers, HttpStatus.OK);			
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

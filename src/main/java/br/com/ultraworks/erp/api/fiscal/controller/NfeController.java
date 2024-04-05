package br.com.ultraworks.erp.api.fiscal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.fiscal.service.NfeTesteService;

@RestController
@RequestMapping("/fiscal/nfe")
public class NfeController {

	private NfeTesteService nfeService;

	@Autowired
	public NfeController(NfeTesteService nfeService) {
		this.nfeService = nfeService;
	}
	
	@GetMapping("/teste/danfe")
	public ResponseEntity<byte[]> downloadDanfeTeste() {
		byte[] pdfBytes = nfeService.gerarDanfeTeste();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDispositionFormData("attachment", "danfe.pdf");
		
		return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
	};
	
	@GetMapping("/teste/{empresaLocalId}")
	public ResponseEntity<byte[]> gerarNFeTeste(@PathVariable Long empresaLocalId) {
		byte[] pdfBytes = nfeService.gerarNFeTeste(empresaLocalId);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDispositionFormData("attachment", "danfe.pdf");
		
		return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
	};
}

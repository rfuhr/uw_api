package br.com.ultraworks.erp.api.fiscal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.fiscal.domain.nfe.cache.ChacheNFeRequest;
import br.com.ultraworks.erp.api.fiscal.domain.nfe.request.NFeRequest;
import br.com.ultraworks.erp.api.fiscal.service.NfeService;

@RestController
@RequestMapping("/fiscal/nfe")
public class NfeController {

	private NfeService nfeService;

	@Autowired
	public NfeController(NfeService nfeService) {
		this.nfeService = nfeService;
	}

//	@GetMapping("/teste/danfe")
//	public ResponseEntity<byte[]> downloadDanfeTeste() {
//		byte[] pdfBytes = nfeService.gerarDanfeTeste();
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_PDF);
//		headers.setContentDispositionFormData("attachment", "danfe.pdf");
//
//		return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
//	};

//	@GetMapping("/teste/{empresaLocalId}")
//	public ResponseEntity<byte[]> gerarNFeTeste(@PathVariable Long empresaLocalId) {
//		byte[] pdfBytes = nfeService.gerarNFeTeste(empresaLocalId);
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_PDF);
//		headers.setContentDispositionFormData("attachment", "danfe.pdf");
//
//		return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
//	};

	@GetMapping("/filial/{empresaFilialId}/iniciar")
	public ResponseEntity<?> getNovaNFe(@PathVariable Long empresaFilialId) {
		if (empresaFilialId == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Identificador da filial da empresa deve ser informado");
		}

		return ResponseEntity.ok(nfeService.iniciarNovaNFe(empresaFilialId));
	}

	@GetMapping("/filial/{empresaFilialId}")
	public ResponseEntity<?> getListaNFe(@PathVariable Long empresaFilialId) {
		if (empresaFilialId == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Identificador da filial da empresa deve ser informado");
		}

		return ResponseEntity.ok(nfeService.getListaNFe(empresaFilialId));
	}

	@PostMapping("/cache")
	public ResponseEntity<?> saveCacheNFe(@RequestBody ChacheNFeRequest cacheNFeRequest) {
		nfeService.saveCache(cacheNFeRequest);
		return ResponseEntity.accepted().build();
	}
	
	@GetMapping("/cache/{cacheId}")
	public ResponseEntity<?> getCacheNFe(@PathVariable Long cacheId) {
		return ResponseEntity.ok(nfeService.getCacheNFe(cacheId));
	}
	
	@PostMapping
	public ResponseEntity<?> emitirNFe(@RequestBody NFeRequest nFeRequest) {
		nfeService.salvarNFe(nFeRequest);
		return ResponseEntity.accepted().build();
	}
	
	@GetMapping("/{nfeId}/enviar")
	public ResponseEntity<byte[]> enviarNFe(@PathVariable Long nfeId) {
		byte[] pdfBytes = nfeService.enviarNFe(nfeId);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDispositionFormData("attachment", "danfe.pdf");

		return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
	};
}

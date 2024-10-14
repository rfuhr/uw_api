package br.com.ultraworks.erp.api.agricola.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.agricola.domain.romaneioagricola.RomaneioAgricola;
import br.com.ultraworks.erp.api.agricola.domain.romaneioagricola.RomaneioAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.mapper.RomaneioAgricolaMapper;
import br.com.ultraworks.erp.api.agricola.service.romaneioagricola.RomaneioAgricolaService;
import br.com.ultraworks.erp.core.generics.GenericController;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/agricola/romaneio-agricola")
public class RomaneioAgricolaController extends GenericController<RomaneioAgricola, Long, RomaneioAgricolaDTO> {

	public RomaneioAgricolaController(RomaneioAgricolaService service, RomaneioAgricolaMapper mapper) {
		super(service, mapper);
	}
	
	@PutMapping("{id}/cancelar")
	@Transactional
	public ResponseEntity<?> cancelar(@PathVariable Long id) {
		((RomaneioAgricolaService) service).cancelarRomaneio(id);
		return ResponseEntity.noContent().build(); 
	}	
}
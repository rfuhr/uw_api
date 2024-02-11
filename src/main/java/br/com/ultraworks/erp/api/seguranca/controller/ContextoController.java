package br.com.ultraworks.erp.api.seguranca.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.seguranca.domain.contexto.ContextoDTO;
import br.com.ultraworks.erp.api.seguranca.service.ContextoService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/seguranca/usuario/{usuarioId}/contexto")
@AllArgsConstructor
public class ContextoController {

	ContextoService contextoService;
	
	@GetMapping
	public ResponseEntity<ContextoDTO> getByUsuario(@PathVariable long usuarioId) {
		return ResponseEntity.ok(contextoService.getContextoDTOByUsuario(usuarioId));
	};
	
    @PostMapping
    @Transactional
    public ResponseEntity<ContextoDTO> alterarContexto(@RequestBody ContextoDTO contextoDTO, @PathVariable("usuarioId") long usuarioId ) {
		ContextoDTO result = contextoService.alterarContexto(usuarioId, contextoDTO);
		return ResponseEntity.ok(result);    	
    }
}

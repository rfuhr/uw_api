package br.com.ultraworks.erp.api.seguranca.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.seguranca.domain.modulo.ModuloDTO;
import br.com.ultraworks.erp.api.seguranca.mapper.ModuloMapper;
import br.com.ultraworks.erp.api.seguranca.service.ModuloService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/seguranca/modulo")
@AllArgsConstructor
public class ModuloController {

	ModuloService moduloService;
	ModuloMapper moduloMapper;
	
	@GetMapping
	public ResponseEntity<List<ModuloDTO>> getAll() {
		List<ModuloDTO> modulos = moduloService.getAll()
        .stream()
        .map(moduloMapper::toDto)
        .collect(Collectors.toList());
		return ResponseEntity.ok(modulos);
	}
}

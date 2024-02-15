package br.com.ultraworks.erp.api.estoque.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.estoque.domain.marca.Marca;
import br.com.ultraworks.erp.api.estoque.domain.marca.MarcaDTO;
import br.com.ultraworks.erp.api.estoque.mapper.MarcaMapper;
import br.com.ultraworks.erp.api.estoque.service.MarcaService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/estoque/marca")
public class MarcaController extends GenericController<Marca, Long, MarcaDTO> {

	public MarcaController(MarcaService service, MarcaMapper mapper) {
		super(service, mapper);
	}

}
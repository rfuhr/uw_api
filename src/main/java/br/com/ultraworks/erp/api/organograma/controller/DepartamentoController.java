package br.com.ultraworks.erp.api.organograma.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.organograma.domain.departamento.Departamento;
import br.com.ultraworks.erp.api.organograma.domain.departamento.DepartamentoDTO;
import br.com.ultraworks.erp.api.organograma.mapper.DepartamentoMapper;
import br.com.ultraworks.erp.api.organograma.service.DepartamentoService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/organograma/departamento")
public class DepartamentoController extends GenericController<Departamento, Long, DepartamentoDTO> {

	public DepartamentoController(DepartamentoService service, DepartamentoMapper mapper) {
		super(service, mapper);
	}
}
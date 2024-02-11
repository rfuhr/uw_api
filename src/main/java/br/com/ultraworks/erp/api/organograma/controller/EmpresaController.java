package br.com.ultraworks.erp.api.organograma.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.organograma.domain.empresa.Empresa;
import br.com.ultraworks.erp.api.organograma.domain.empresa.EmpresaDTO;
import br.com.ultraworks.erp.api.organograma.mapper.EmpresaMapper;
import br.com.ultraworks.erp.api.organograma.service.EmpresaService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/organograma/empresa")
public class EmpresaController extends GenericController<Empresa, Long, EmpresaDTO> {

	public EmpresaController(EmpresaService service, EmpresaMapper mapper) {
		super(service, mapper);
	}
}
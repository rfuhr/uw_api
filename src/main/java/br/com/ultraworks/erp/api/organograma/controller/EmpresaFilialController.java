package br.com.ultraworks.erp.api.organograma.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.organograma.domain.empresaFilial.EmpresaFilial;
import br.com.ultraworks.erp.api.organograma.domain.empresaFilial.EmpresaFilialDTO;
import br.com.ultraworks.erp.api.organograma.mapper.EmpresaFilialMapper;
import br.com.ultraworks.erp.api.organograma.service.EmpresaFilialService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/organograma/empresa-filial")
public class EmpresaFilialController extends GenericController<EmpresaFilial, Long, EmpresaFilialDTO> {

	public EmpresaFilialController(EmpresaFilialService service, EmpresaFilialMapper mapper) {
		super(service, mapper);
	}
}
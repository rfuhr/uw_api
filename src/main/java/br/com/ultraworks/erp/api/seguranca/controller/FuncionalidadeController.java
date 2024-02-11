package br.com.ultraworks.erp.api.seguranca.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.seguranca.domain.funcionalidade.Funcionalidade;
import br.com.ultraworks.erp.api.seguranca.domain.funcionalidade.FuncionalidadeDTO;
import br.com.ultraworks.erp.api.seguranca.mapper.FuncionalidadeMapper;
import br.com.ultraworks.erp.api.seguranca.service.FuncionalidadeService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/seguranca/funcionalidade")
public class FuncionalidadeController extends GenericController<Funcionalidade, Long, FuncionalidadeDTO> {

	public FuncionalidadeController(FuncionalidadeService service, FuncionalidadeMapper mapper) {
		super(service, mapper);
	}
}
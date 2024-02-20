package br.com.ultraworks.erp.api.fiscal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.fiscal.domain.classificacaooperacao.ClassificacaoOperacao;
import br.com.ultraworks.erp.api.fiscal.domain.classificacaooperacao.ClassificacaoOperacaoDTO;
import br.com.ultraworks.erp.api.fiscal.mapper.ClassificacaoOperacaoMapper;
import br.com.ultraworks.erp.api.fiscal.service.ClassificacaoOperacaoService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/fiscal/classificacao-operacao")
public class ClassificacaoOperacaoController extends GenericController<ClassificacaoOperacao, Long, ClassificacaoOperacaoDTO> {

	public ClassificacaoOperacaoController(ClassificacaoOperacaoService service, ClassificacaoOperacaoMapper mapper) {
		super(service, mapper);
	}

}
package br.com.ultraworks.erp.api.financeiro.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.financeiro.domain.operacaoacessoriafinanceira.OperacaoAcessoriaFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.operacaoacessoriafinanceira.OperacaoAcessoriaFinanceiraDTO;
import br.com.ultraworks.erp.api.financeiro.mapper.OperacaoAcessoriaFinanceiraMapper;
import br.com.ultraworks.erp.api.financeiro.service.OperacaoAcessoriaFinanceiraService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/financeiro/operacao-acessoria-financeira")
public class OperacaoAcessoriaFinanceiraController
		extends GenericController<OperacaoAcessoriaFinanceira, Long, OperacaoAcessoriaFinanceiraDTO> {

	private OperacaoAcessoriaFinanceiraMapper operacaoAcessoriaFinanceiraMapper;

	public OperacaoAcessoriaFinanceiraController(OperacaoAcessoriaFinanceiraService service,
			OperacaoAcessoriaFinanceiraMapper mapper) {
		super(service, mapper);
		operacaoAcessoriaFinanceiraMapper = mapper;
	}

	@GetMapping("/juros")
	public ResponseEntity<?> getAllJuros() {
		List<OperacaoAcessoriaFinanceiraDTO> entities = ((OperacaoAcessoriaFinanceiraService) service).getAllJuros()
				.stream().map(operacaoAcessoriaFinanceiraMapper::toDto).collect(Collectors.toList());
		return ResponseEntity.ok(entities);
	}
	
	@GetMapping("/descontos")
	public ResponseEntity<?> getAllDescontos() {
		List<OperacaoAcessoriaFinanceiraDTO> entities = ((OperacaoAcessoriaFinanceiraService) service).getAllDescontos()
				.stream().map(operacaoAcessoriaFinanceiraMapper::toDto).collect(Collectors.toList());
		return ResponseEntity.ok(entities);
	}

}
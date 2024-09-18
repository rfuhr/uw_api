package br.com.ultraworks.erp.api.financeiro.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.financeiro.domain.negociacao.NegociacaoFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.negociacao.NegociacaoFinanceiraDTO;
import br.com.ultraworks.erp.api.financeiro.mapper.NegociacaoFinanceiraMapper;
import br.com.ultraworks.erp.api.financeiro.service.NegociacaoFinanceiraService;
import br.com.ultraworks.erp.core.generics.GenericController;
import br.com.ultraworks.erp.core.util.ResponseUtil;

@RestController
@RequestMapping("/financeiro/negociacao")
public class NegociacaoFinanceiraController
		extends GenericController<NegociacaoFinanceira, Long, NegociacaoFinanceiraDTO> {

	public NegociacaoFinanceiraController(NegociacaoFinanceiraService service, NegociacaoFinanceiraMapper mapper) {
		super(service, mapper);
	}

	@PostMapping
	public ResponseEntity<NegociacaoFinanceiraDTO> create(@RequestBody NegociacaoFinanceiraDTO dto) {
		validate(dto);
		NegociacaoFinanceira entity = mapper.toNewEntity(dto);
		entity = ((NegociacaoFinanceiraService) service).inserirNegociacao(entity);
		NegociacaoFinanceiraDTO dto2 = mapper.toDto(entity);
		return ResponseUtil.wrapOrNotFound(Optional.of(dto2));
	}
}
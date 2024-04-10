package br.com.ultraworks.erp.api.fiscal.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.fiscal.domain.cfop.Cfop;
import br.com.ultraworks.erp.api.fiscal.domain.cfop.CfopDTO;
import br.com.ultraworks.erp.api.fiscal.mapper.CfopMapper;
import br.com.ultraworks.erp.api.fiscal.service.CfopService;
import br.com.ultraworks.erp.core.dto.LazyParams;
import br.com.ultraworks.erp.core.dto.ResultPage;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/fiscal/cfop")
public class CfopController extends GenericController<Cfop, Long, CfopDTO> {

	public CfopController(CfopService service, CfopMapper mapper) {
		super(service, mapper);
	}

	@PostMapping("/seletores/by-operacao-interna/{operacaoInternaId}")
	public ResponseEntity<ResultPage<CfopDTO>> getAllPaginadabyOperacaoInternaId(@PathVariable Long operacaoInternaId, @RequestBody LazyParams params) {
		Page<Cfop> resultados = ((CfopService) service).getAllPaginadabyOperacaoInternaId(operacaoInternaId, params);

		ResultPage<CfopDTO> resultado = new ResultPage<CfopDTO>(mapper.toDto(resultados.getContent()),
				resultados.getTotalElements(), resultados.getPageable().getPageNumber(),
				resultados.getPageable().getPageSize(), resultados.getTotalPages());
		return ResponseEntity.ok(resultado);
	}
}
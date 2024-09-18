package br.com.ultraworks.erp.api.financeiro.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.financeiro.domain.movimentocaixabanco.MovimentoCaixaBanco;
import br.com.ultraworks.erp.api.financeiro.domain.movimentocaixabanco.MovimentoCaixaBancoDTO;
import br.com.ultraworks.erp.api.financeiro.mapper.MovimentoCaixaBancoMapper;
import br.com.ultraworks.erp.api.financeiro.service.MovimentoCaixaBancoService;
import br.com.ultraworks.erp.core.generics.GenericController;
import br.com.ultraworks.erp.core.util.ResponseUtil;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/financeiro/movimento-caixa-banco")
public class MovimentoCaixaBancoController
		extends GenericController<MovimentoCaixaBanco, Long, MovimentoCaixaBancoDTO> {

	public MovimentoCaixaBancoController(MovimentoCaixaBancoService service, MovimentoCaixaBancoMapper mapper) {
		super(service, mapper);
	}

	@PostMapping
	public ResponseEntity<MovimentoCaixaBancoDTO> create(@RequestBody MovimentoCaixaBancoDTO dto) {
		validate(dto);
		MovimentoCaixaBanco entity = mapper.toNewEntity(dto);
		entity = ((MovimentoCaixaBancoService) service).inserirMovimento(entity);
		MovimentoCaixaBancoDTO dto2 = mapper.toDto(entity);
		return ResponseUtil.wrapOrNotFound(Optional.of(dto2));
	}
}
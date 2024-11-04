package br.com.ultraworks.erp.api.estoque.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.estoque.domain.validadepartamentogrupocontabil.ValidaDepartamentoGrupoContabil;
import br.com.ultraworks.erp.api.estoque.domain.validadepartamentogrupocontabil.ValidaDepartamentoGrupoContabilDTO;
import br.com.ultraworks.erp.api.estoque.mapper.ValidaDepartamentoGrupoContabilMapper;
import br.com.ultraworks.erp.api.estoque.service.ValidaDepartamentoGrupoContabilService;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/estoque/valida-departamento-grupo-contabil")
public class ValidaDepartamentoGrupoContabilController
		extends GenericController<ValidaDepartamentoGrupoContabil, Long, ValidaDepartamentoGrupoContabilDTO> {

	public ValidaDepartamentoGrupoContabilController(ValidaDepartamentoGrupoContabilService service,
			ValidaDepartamentoGrupoContabilMapper mapper) {
		super(service, mapper);
	}

	@GetMapping("/servicos/get-validacao")
	public ResponseEntity<ValidaDepartamentoGrupoContabilDTO> getValidacao(
			@RequestParam(value = "departamento") Long departamentoId, @RequestParam(value = "grupocontabil") Long grupoContabilId) {
		Optional<ValidaDepartamentoGrupoContabil> validacao = ((ValidaDepartamentoGrupoContabilService) service)
				.getValidacao(departamentoId, grupoContabilId);

		if (validacao.isPresent()) {
			return ResponseEntity.ok(mapper.toDto(validacao.get()));
		} else {
			throw  new RegisterNotFoundException("Não encontrado validação de departamento x grupo contábil");
		}
	}
}
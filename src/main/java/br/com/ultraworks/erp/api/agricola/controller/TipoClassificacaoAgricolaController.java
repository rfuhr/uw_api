package br.com.ultraworks.erp.api.agricola.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.agricola.domain.tipoclassificacaoagricola.TipoClassificacaoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.tipoclassificacaoagricola.TipoClassificacaoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.mapper.TipoClassificacaoAgricolaMapper;
import br.com.ultraworks.erp.api.agricola.service.TipoClassificacaoAgricolaService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/agricola/tipo-classificacao-agricola")
public class TipoClassificacaoAgricolaController
		extends GenericController<TipoClassificacaoAgricola, Long, TipoClassificacaoAgricolaDTO> {

	public TipoClassificacaoAgricolaController(TipoClassificacaoAgricolaService service,
			TipoClassificacaoAgricolaMapper mapper) {
		super(service, mapper);
	}

	@GetMapping("/servicos/tipos-configurados/produto/{produtoId}/safra/{safraId}")
	public ResponseEntity<?> getBuscaTiposConfigurados(@PathVariable Long produtoId, @PathVariable Long safraId) {
		List<TipoClassificacaoAgricola> listaTiposAgricola = ((TipoClassificacaoAgricolaService) service)
				.getBuscaTiposConfigurados(produtoId, safraId);

		return ResponseEntity.ok(mapper.toDto(listaTiposAgricola));
	}
}
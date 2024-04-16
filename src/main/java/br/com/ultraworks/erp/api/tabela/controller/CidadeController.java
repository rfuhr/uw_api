package br.com.ultraworks.erp.api.tabela.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.tabela.domain.cidade.Cidade;
import br.com.ultraworks.erp.api.tabela.domain.cidade.CidadeDTO;
import br.com.ultraworks.erp.api.tabela.domain.uf.Uf;
import br.com.ultraworks.erp.api.tabela.domain.uf.UfDTO;
import br.com.ultraworks.erp.api.tabela.mapper.CidadeMapper;
import br.com.ultraworks.erp.api.tabela.service.CidadeService;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/tabela/cidade")
public class CidadeController extends GenericController<Cidade, Long, CidadeDTO> {

	public CidadeController(CidadeService service, CidadeMapper mapper) {
		super(service, mapper);
	}

	@GetMapping("/ibge/{ibge}")
	public ResponseEntity<CidadeDTO> getByIbge(@PathVariable Long ibge) {
		Optional<Cidade> cidade = ((CidadeService) service).getByIbge(ibge);
		if (cidade.isPresent()) {
			return ResponseEntity.ok(mapper.toDto(cidade.get()));
		} else {
			throw  new RegisterNotFoundException("Não encontrado cidade");
		}
	};
}
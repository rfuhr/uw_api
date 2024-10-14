package br.com.ultraworks.erp.api.tabela.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.tabela.domain.regraatividade.RegraAtividade;
import br.com.ultraworks.erp.api.tabela.domain.regraatividade.RegraAtividadeDTO;
import br.com.ultraworks.erp.api.tabela.mapper.RegraAtividadeMapper;
import br.com.ultraworks.erp.api.tabela.service.RegraAtividadeService;
import br.com.ultraworks.erp.core.dto.LazyParams;
import br.com.ultraworks.erp.core.dto.ResultPage;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/tabela/regra-atividade")
public class RegraAtividadeController extends GenericController<RegraAtividade, Long, RegraAtividadeDTO> {

	public RegraAtividadeController(RegraAtividadeService service, RegraAtividadeMapper mapper) {
		super(service, mapper);
	}

	@GetMapping("proximo-codigo")
	public ResponseEntity<?> getProximoCodigo() {
		return ResponseEntity.ok(((RegraAtividadeService) service).getProximoCodigo());
	}
	
	@PostMapping("/seletores/by-parceiro/{parceiroId}")
	public ResponseEntity<ResultPage<RegraAtividadeDTO>> getAllPaginadaByParceiro(@PathVariable Long parceiroId, @RequestBody LazyParams params) {
		Page<RegraAtividade> resultados = ((RegraAtividadeService) service).getAllPaginadaByParceiro(parceiroId, params);

		if (!resultados.isEmpty()) {
			ResultPage<RegraAtividadeDTO> resultado = new ResultPage<RegraAtividadeDTO>(mapper.toDto(resultados.getContent()),
					resultados.getTotalElements(), resultados.getPageable().getPageNumber(),
					resultados.getPageable().getPageSize(), resultados.getTotalPages());
			return ResponseEntity.ok(resultado);
		} else {
			return ResponseEntity.ok(ResultPage.empty());
		}
	}	
}

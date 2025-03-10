package br.com.ultraworks.erp.api.compras.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.compras.domain.cotacaomercadoria.CotacaoMercadoria;
import br.com.ultraworks.erp.api.compras.domain.cotacaomercadoria.CotacaoMercadoriaDTO;
import br.com.ultraworks.erp.api.compras.mapper.CotacaoMercadoriaMapper;
import br.com.ultraworks.erp.api.compras.request.RequestInformaRetornoCotacao;
import br.com.ultraworks.erp.api.compras.service.cotacaomercadoria.CotacaoMercadoriaService;
import br.com.ultraworks.erp.api.compras.vo.CotacaoMercadoriaParaRetornoVO;
import br.com.ultraworks.erp.core.exception.RegisterNotFoundException;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/compras/cotacao-mercadoria")
public class CotacaoMercadoriaController
		extends GenericController<CotacaoMercadoria, Long, CotacaoMercadoriaDTO> {

	public CotacaoMercadoriaController(CotacaoMercadoriaService service, CotacaoMercadoriaMapper mapper) {
		super(service, mapper);
	}

	@GetMapping("/servicos/get-cotacao-retorno/{cotacaoMercadoriaId}/{cotacaoMercadoriaParceiroId}")
	public ResponseEntity<CotacaoMercadoriaDTO> buscarCotacaoParaRetorno(@PathVariable(name = "cotacaoMercadoriaId") Long cotacaoMercadoriaId,
			@PathVariable(name = "cotacaoMercadoriaParceiroId") Long cotacaoMercadoriaParceiroId) {
		Optional<CotacaoMercadoria> optCotacao = ((CotacaoMercadoriaService) service)
				.buscarCotacaoParaRetorno(cotacaoMercadoriaId, cotacaoMercadoriaParceiroId);

		if (optCotacao.isPresent()) {
			return ResponseEntity.ok(mapper.toDto(optCotacao.get()));
		} else {
			throw  new RegisterNotFoundException("Não encontrado registro");
		}	}
	
	@GetMapping("/servicos/get-cotacoes-retorno")
	public ResponseEntity<List<CotacaoMercadoriaParaRetornoVO>> buscarCotacoesPendenteParaRetorno() {
		List<CotacaoMercadoriaParaRetornoVO> cotacoes = ((CotacaoMercadoriaService) service)
				.buscarCotacoesPendenteParaRetorno();

		if (!cotacoes.isEmpty()) {
			return ResponseEntity.ok(cotacoes);
		} else {
			return ResponseEntity.ok(Collections.emptyList());
		}
	}
	
	@PostMapping("/servicos/informar-retorno")
	public ResponseEntity<?> informarRetorno(@RequestBody RequestInformaRetornoCotacao requestInformaRetornoCotacao) {
		 ((CotacaoMercadoriaService) service)
				.informarRetorno(requestInformaRetornoCotacao);
		 return ResponseEntity.noContent().build();	
	}
}
package br.com.ultraworks.erp.api.compras.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.compras.domain.solicitacaomercadoria.SolicitacaoMercadoria;
import br.com.ultraworks.erp.api.compras.domain.solicitacaomercadoria.SolicitacaoMercadoriaDTO;
import br.com.ultraworks.erp.api.compras.mapper.SolicitacaoMercadoriaMapper;
import br.com.ultraworks.erp.api.compras.service.solicitacaomercadoria.SolicitacaoMercadoriaService;
import br.com.ultraworks.erp.api.compras.vo.SolicitacaoMercadoriaParaCotacaoVO;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/compras/solicitacao-mercadoria")
public class SolicitacaoMercadoriaController
		extends GenericController<SolicitacaoMercadoria, Long, SolicitacaoMercadoriaDTO> {

	public SolicitacaoMercadoriaController(SolicitacaoMercadoriaService service, SolicitacaoMercadoriaMapper mapper) {
		super(service, mapper);
	}

	@GetMapping("/servicos/get-solicitacoes-pendentes-cotacao")
	public ResponseEntity<List<SolicitacaoMercadoriaParaCotacaoVO>> buscarSolicitacoesPendentesParaCotacao(
			@RequestParam(value = "departamentosolicitado") Long departamentoSolicitadoId) {
		List<SolicitacaoMercadoriaParaCotacaoVO> solicitacoes = ((SolicitacaoMercadoriaService) service)
				.buscarSolicitacoesPendentesParaCotacao(departamentoSolicitadoId);

		if (!solicitacoes.isEmpty()) {
			return ResponseEntity.ok(solicitacoes);
		} else {
			return ResponseEntity.ok(Collections.emptyList());
		}
	}
}
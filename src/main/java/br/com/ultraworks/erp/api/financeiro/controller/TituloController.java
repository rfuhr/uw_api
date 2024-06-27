package br.com.ultraworks.erp.api.financeiro.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultraworks.erp.api.financeiro.domain.titulo.TituloDTO;
import br.com.ultraworks.erp.api.financeiro.domain.titulo.request.BaixaEstornoRequest;
import br.com.ultraworks.erp.api.financeiro.domain.titulo.request.BaixaTituloRequest;
import br.com.ultraworks.erp.api.financeiro.domain.titulo.request.LancamentoTituloRequest;
import br.com.ultraworks.erp.api.financeiro.domain.titulo.request.NegociacaoRequest;
import br.com.ultraworks.erp.api.financeiro.domain.titulo.request.SelecaoBaixaTituloRequest;
import br.com.ultraworks.erp.api.financeiro.domain.titulo.request.SelecaoConsultaTituloRequest;
import br.com.ultraworks.erp.api.financeiro.domain.titulo.request.SelecaoEstornoTituloRequest;
import br.com.ultraworks.erp.api.financeiro.domain.titulo.request.SelecaoNegociacaoRequest;
import br.com.ultraworks.erp.api.financeiro.domain.titulo.request.SelecaoSubstituicaoCarteiraRequest;
import br.com.ultraworks.erp.api.financeiro.domain.titulo.request.SubstituicaoCarteiraRequest;
import br.com.ultraworks.erp.api.financeiro.domain.titulo.response.SelecaoBaixaResponse;
import br.com.ultraworks.erp.api.financeiro.domain.titulo.response.SelecaoConsultaResponse;
import br.com.ultraworks.erp.api.financeiro.domain.titulo.response.SelecaoEstornoResponse;
import br.com.ultraworks.erp.api.financeiro.domain.titulo.response.SelecaoNegociacaoResponse;
import br.com.ultraworks.erp.api.financeiro.domain.titulo.response.SelecaoSubstituicaoCarteiraResponse;
import br.com.ultraworks.erp.api.financeiro.service.TituloService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/financeiro/titulo")
public class TituloController {

	private TituloService tituloService;

	public TituloController(TituloService tituloService) {
		this.tituloService = tituloService;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<?> create(@RequestBody LancamentoTituloRequest lancamentoTituloRequest) {
		tituloService.inserirTitulo(lancamentoTituloRequest);
		return ResponseEntity.accepted().build();
	}

	@GetMapping("/{tituloId}")
	@Transactional
	public ResponseEntity<?> consultaTitulo(@PathVariable Long tituloId) {
		TituloDTO tituloDTO = tituloService.consultaTitulo(tituloId);
		return ResponseEntity.ok(tituloDTO);
	}

	@PostMapping("/consulta/selecao")
	@Transactional
	public ResponseEntity<?> selecaoConsulta(@RequestBody SelecaoConsultaTituloRequest selecaoConsultaTituloRequest) {
		List<SelecaoConsultaResponse> selecaoConsulta = tituloService.selecaoConsulta(selecaoConsultaTituloRequest);
		return ResponseEntity.ok(selecaoConsulta);
	}

	@PostMapping("/baixa/selecao")
	@Transactional
	public ResponseEntity<?> selecaoBaixa(@RequestBody SelecaoBaixaTituloRequest selecaoBaixaTituloRequest) {
		List<SelecaoBaixaResponse> selecaoBaixa = tituloService.selecaoBaixa(selecaoBaixaTituloRequest);
		return ResponseEntity.ok(selecaoBaixa);
	}

	@PostMapping("/baixa")
	@Transactional
	public ResponseEntity<?> baixa(@RequestBody BaixaTituloRequest baixaTituloRequest) {
		tituloService.realizarBaixa(baixaTituloRequest);
		return ResponseEntity.accepted().build();
	}

	@PostMapping("/estorno/selecao")
	@Transactional
	public ResponseEntity<?> selecaoEstornobaixa(@RequestBody SelecaoEstornoTituloRequest selecaoEstornoTituloRequest) {
		List<SelecaoEstornoResponse> selecaoEstorno = tituloService.selecaoEstorno(selecaoEstornoTituloRequest);
		return ResponseEntity.ok(selecaoEstorno);
	}

	@PostMapping("/estorno")
	@Transactional
	public ResponseEntity<?> estorno(@RequestBody BaixaEstornoRequest baixaEstornoRequest) {
		tituloService.realizarEstorno(baixaEstornoRequest);
		return ResponseEntity.accepted().build();
	}

	@PostMapping("/substituicao-carteira/selecao")
	@Transactional
	public ResponseEntity<?> selecaoSubstituicaoCarteira(
			@RequestBody SelecaoSubstituicaoCarteiraRequest selecaoSubstituicaoCarteiraRequest) {
		List<SelecaoSubstituicaoCarteiraResponse> selecaoSubstituicao = tituloService
				.selecaoSubstituicaoCarteira(selecaoSubstituicaoCarteiraRequest);
		return ResponseEntity.ok(selecaoSubstituicao);
	}

	@PostMapping("/substituicao-carteira")
	@Transactional
	public ResponseEntity<?> substituicaoCarteira(
			@RequestBody SubstituicaoCarteiraRequest substituicaoCarteiraRequest) {
		tituloService.realizarSubstituicaoCarteira(substituicaoCarteiraRequest);
		return ResponseEntity.accepted().build();
	}

	@PostMapping("/negociacao/selecao")
	@Transactional
	public ResponseEntity<?> selecaoNegociacao(@RequestBody SelecaoNegociacaoRequest selecaoNegociacaoRequest) {
		List<SelecaoNegociacaoResponse> selecaoNegociacao = tituloService.selecaoNegociacao(selecaoNegociacaoRequest);
		return ResponseEntity.ok(selecaoNegociacao);
	}

	@PostMapping("/negociacao")
	@Transactional
	public ResponseEntity<?> baixa(@RequestBody NegociacaoRequest negociacaoRequest) {
		tituloService.realizarNegociacao(negociacaoRequest);
		return ResponseEntity.accepted().build();
	}
}

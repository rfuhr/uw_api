package br.com.ultraworks.erp.api.agricola.service.contratoagricola;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.agricola.domain.contratoagricola.ContratoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.contratoagricola.ContratoAgricolaDTO;
import br.com.ultraworks.erp.api.agricola.domain.contratoagricola.ContratoAgricolaSelecaoResponse;
import br.com.ultraworks.erp.api.agricola.domain.contratoagricoladesconto.CalculoDescontoContratoRequest;
import br.com.ultraworks.erp.api.agricola.domain.contratoagricoladesconto.CalculoDescontoContratoResponse;
import br.com.ultraworks.erp.api.agricola.domain.taxacalculoagricola.TaxaCalculoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.tipocalculoagricola.TipoCalculoAgricola;
import br.com.ultraworks.erp.api.agricola.domain.tipotaxaagricola.TipoTaxaAgricola;
import br.com.ultraworks.erp.api.agricola.domain.validacalculoagricola.ValidaCalculoAgricola;
import br.com.ultraworks.erp.api.agricola.mapper.ContratoAgricolaMapper;
import br.com.ultraworks.erp.api.agricola.repository.ContratoAgricolaRepository;
import br.com.ultraworks.erp.api.agricola.repository.query.SelecionaContratoAgricolaParaFixacaoQuery;
import br.com.ultraworks.erp.api.agricola.repository.query.SelecionaContratoAgricolaParaRomaneioQuery;
import br.com.ultraworks.erp.api.agricola.service.TaxaCalculoAgricolaService;
import br.com.ultraworks.erp.api.agricola.service.ValidaCalculoAgricolaService;
import br.com.ultraworks.erp.core.exception.BusinessException;
import br.com.ultraworks.erp.core.generics.GenericService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ContratoAgricolaService extends GenericService<ContratoAgricola, Long, ContratoAgricolaDTO> {

	private SelecionaContratoAgricolaParaRomaneioQuery selecionaContratoAgricolaParaRomaneioQuery;
	private SelecionaContratoAgricolaParaFixacaoQuery selecionaContratoAgricolaParaFixacaoQuery;
	private ValidaCalculoAgricolaService validaCalculoAgricolaService;
	private TaxaCalculoAgricolaService taxaAgricolaService;

	@Autowired
	public ContratoAgricolaService(ContratoAgricolaRepository repository, ContratoAgricolaMapper mapper,
			SelecionaContratoAgricolaParaRomaneioQuery selecionaContratoAgricolaParaRomaneioQuery,
			SelecionaContratoAgricolaParaFixacaoQuery selecionaContratoAgricolaParaFixacaoQuery,
			ValidaCalculoAgricolaService validaCalculoAgricolaService, TaxaCalculoAgricolaService taxaAgricolaService) {
		super(repository, mapper);
		this.selecionaContratoAgricolaParaRomaneioQuery = selecionaContratoAgricolaParaRomaneioQuery;
		this.selecionaContratoAgricolaParaFixacaoQuery = selecionaContratoAgricolaParaFixacaoQuery;
		this.validaCalculoAgricolaService = validaCalculoAgricolaService;
		this.taxaAgricolaService = taxaAgricolaService;
	}

	public List<ContratoAgricolaSelecaoResponse> buscarParaRomaneio(Long parceiroId, Long itemId, Long safraId) {
		return selecionaContratoAgricolaParaRomaneioQuery.executeSQL(parceiroId, itemId, safraId);
	}

	public List<ContratoAgricolaSelecaoResponse> buscarParaFixacao(Long parceiroId, Long itemId) {
		return selecionaContratoAgricolaParaFixacaoQuery.executeSQL(parceiroId, itemId);
	}

	public List<CalculoDescontoContratoResponse> calcularDescontos(CalculoDescontoContratoRequest request) {
		List<CalculoDescontoContratoResponse> descontos = new ArrayList<>();

		List<ValidaCalculoAgricola> validacoes = validaCalculoAgricolaService.getValidacoesVigente(request.getItemId(),
				request.getOperacaoInternaId(), request.getGrupoOperacaoAgricolaId(), request.getRegraAtividadeId(),
				request.getDataBase());
		List<TipoCalculoAgricola> tiposCalculos = validacoes.stream().map(ValidaCalculoAgricola::getTipoCalculoAgricola)
				.collect(Collectors.toList());
		tiposCalculos.forEach(tipo -> {
			BigDecimal valor = BigDecimal.ZERO;
			TaxaCalculoAgricola taxaCalculoAgricola = taxaAgricolaService.getValidacaoVigente(request.getItemId(),
					tipo.getId(), request.getRegraAtividadeId(), BigDecimal.valueOf(9999999), request.getDataBase());
			if (taxaCalculoAgricola.getTipoTaxaAgricola().equals(TipoTaxaAgricola.PERCENTUAL)) {
				valor = request.getValorBruto().multiply(taxaCalculoAgricola.getFatorCalculo())
						.divide(new BigDecimal("100"), 0, RoundingMode.HALF_UP);
			} else if (taxaCalculoAgricola.getTipoTaxaAgricola().equals(TipoTaxaAgricola.INDICE)) {
				valor = request.getValorBruto().multiply(taxaCalculoAgricola.getFatorCalculo());
			} else {
				valor = taxaCalculoAgricola.getFatorCalculo();
			}

			CalculoDescontoContratoResponse desconto = CalculoDescontoContratoResponse.builder()
					.contratoAgricolaId(request.getContratoAgricolaId()).itemId(request.getItemId())
					.departamentoId(request.getDepartamentoId()).tipoCalculoAgricolaId(tipo.getId())
					.percentualTaxaContrato(taxaCalculoAgricola.getFatorCalculo())
					.percentualTaxaAtual(taxaCalculoAgricola.getFatorCalculo()).valor(valor).build();
			descontos.add(desconto);
		});
		return descontos;
	}
	
	@Override
	public ContratoAgricola save(ContratoAgricola contratoAgricola) {
		if (contratoAgricola.getId() != null) {
			throw new BusinessException("Não é possível alterar um contrato agrícola");
		}		
		return null;
	}
}
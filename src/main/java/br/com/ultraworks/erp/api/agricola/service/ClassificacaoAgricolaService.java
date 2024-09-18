package br.com.ultraworks.erp.api.agricola.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.agricola.domain.classificacaoagricola.RecalcularClassificacaoAgricolaRequest;
import br.com.ultraworks.erp.api.agricola.domain.classificacaoagricola.RecalcularClassificacaoAgricolaResponse;
import br.com.ultraworks.erp.api.agricola.domain.configclassificacaoagricola.ConfigClassificacaoAgricola;

@Service
public class ClassificacaoAgricolaService {

	private ConfigClassificacaoAgricolaService configClassificacaoAgricolaService;

	public ClassificacaoAgricolaService(ConfigClassificacaoAgricolaService configClassificacaoAgricolaService) {
		this.configClassificacaoAgricolaService = configClassificacaoAgricolaService;
	}

	public RecalcularClassificacaoAgricolaResponse recalcular(
			RecalcularClassificacaoAgricolaRequest classificaoAgricola) {
		ConfigClassificacaoAgricola configClassificacaoAgricola = configClassificacaoAgricolaService
				.findByClassificacaoAgricola(classificaoAgricola.getTipoClassificacaoAgricolaId(),
						classificaoAgricola.getProdutoId(), classificaoAgricola.getSafraId(),
						classificaoAgricola.getValor());

		if (configClassificacaoAgricola != null) {
			Long pesoBase = classificaoAgricola.getPesoBaseCalculo();
			Long quantidadeDesconto = 0L;
			BigDecimal percentualDesconto = BigDecimal.ZERO;
			if (configClassificacaoAgricola.isDesconto()) {
				BigDecimal pesoBaseDecimal = new BigDecimal(pesoBase);
				percentualDesconto = configClassificacaoAgricola.getPercentualDesconto();
				BigDecimal valorDesconto = pesoBaseDecimal.multiply(percentualDesconto).divide(new BigDecimal(100));
				valorDesconto = valorDesconto.setScale(0, RoundingMode.HALF_UP);
				quantidadeDesconto = valorDesconto.longValue();
			}

			return RecalcularClassificacaoAgricolaResponse.builder().produtoId(classificaoAgricola.getProdutoId())
					.safraId(classificaoAgricola.getTipoClassificacaoAgricolaId())
					.tipoClassificacaoAgricolaId(classificaoAgricola.getTipoClassificacaoAgricolaId())
					.pesoBaseCalculo(classificaoAgricola.getPesoBaseCalculo()).valor(classificaoAgricola.getValor())
					.faixaInicial(configClassificacaoAgricola.getFaixaFinal())
					.faixaFinal(configClassificacaoAgricola.getFaixaFinal()).quantidadeDesconto(quantidadeDesconto)
					.percentualDesconto(percentualDesconto)
					.configClassificacaoAgricolaId(configClassificacaoAgricola.getId()).build();
		} else {
			return RecalcularClassificacaoAgricolaResponse.builder().produtoId(classificaoAgricola.getProdutoId())
					.safraId(classificaoAgricola.getTipoClassificacaoAgricolaId())
					.tipoClassificacaoAgricolaId(classificaoAgricola.getTipoClassificacaoAgricolaId())
					.pesoBaseCalculo(classificaoAgricola.getPesoBaseCalculo()).valor(classificaoAgricola.getValor())
					.faixaInicial(BigDecimal.ZERO).faixaFinal(BigDecimal.ZERO).quantidadeDesconto(0L)
					.percentualDesconto(BigDecimal.ZERO).configClassificacaoAgricolaId(0L).build();

		}
	}
	
	public List<RecalcularClassificacaoAgricolaResponse> recalcular(
			List<RecalcularClassificacaoAgricolaRequest> listaClassificaoAgricola) {
	
		List<RecalcularClassificacaoAgricolaResponse> listaResponse = new ArrayList<>();
		listaClassificaoAgricola.forEach(f -> {
			listaResponse.add(recalcular(f));
		});
	
		return listaResponse;
	}
}

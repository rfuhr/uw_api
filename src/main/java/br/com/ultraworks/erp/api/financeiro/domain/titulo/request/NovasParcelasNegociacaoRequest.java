package br.com.ultraworks.erp.api.financeiro.domain.titulo.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NovasParcelasNegociacaoRequest {

	private int numeroParcela;
	private LocalDate dataVencimento;
	private BigDecimal valorParcela;
}

package br.com.ultraworks.erp.api.financeiro.domain.titulo.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class LancamentoParcelaRequest {

	private int numeroParcela;
	private LocalDate dataVencimento;
	private BigDecimal valorParcela;
}

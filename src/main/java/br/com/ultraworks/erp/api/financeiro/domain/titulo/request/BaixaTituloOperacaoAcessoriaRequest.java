package br.com.ultraworks.erp.api.financeiro.domain.titulo.request;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BaixaTituloOperacaoAcessoriaRequest {

	private Long operacaoAcessoriaFinanceiraId;
	private BigDecimal valor;
}

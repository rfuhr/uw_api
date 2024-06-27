package br.com.ultraworks.erp.api.financeiro.domain.titulo.response;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SelecaoConsultaResponse {

	private Long tituloId;
	private String tipoTituloSigla;
	private LocalDate dataDocumento;
	private String departamentoSigla;
	private Long nossoNumero;
	private String parceiroNome;
	private String parceiroLocalNome;
	private String grupoFinanceiroSigla;
	private String fatoGeradorSigla;
	private String caracteristicaMovimentoFinanceiroSigla;
	private String historicoPadraoSigla;
	private BigDecimal valorTitulo;
}

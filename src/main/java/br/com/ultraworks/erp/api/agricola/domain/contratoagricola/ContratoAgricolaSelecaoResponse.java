package br.com.ultraworks.erp.api.agricola.domain.contratoagricola;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContratoAgricolaSelecaoResponse {

	private Long contratoId;
	private Long parceiroId;
	private String parceiroNome;
	private Long parceiroLocalId;
	private String parceiroLocalNome;
	private Long itemCodigo;
	private Long departamentoId;
	private String departamentoSigla;
	private String departamentoNome;
	private int contratoNumero;
	private BigDecimal contratoSaldo;
	private LocalDate contratoDataDocumento;
	private Long operacaoInternaId;
	private String operacaoInternaSigla;
	private String operacaoInternaNome;
	private String contratoObservacao;
	private Long grupoOperacaoAgricolaId;
	private String grupoOperacaoAgricolaNome;
	private BigDecimal contratoValorUnitarioLiquido;
	private BigDecimal contratoValorUnitarioBruto;
	private Long ufId;
	private String ufSigla;
}

package br.com.ultraworks.erp.api.agricola.domain.contratoagricola;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class ContratoAgricolaDTO {

	private Long id;
	private int numero;
	private Long departamentoId;
	private String departamentoNome;

	private Long itemId;
	private String itemNome;

	private Long parceiroLocalId;
	private String parceiroLocalNome;

	private Long avalistaId;
	private String avalistaNome;

	private Long indiceFinanceiroId;
	private String indiceFinanceiroNome;

	private Long tipoContratoAgricolaId;
	private String tipoContratoAgricola;

	private Long tipoPrecoAgricolaId;
	private String tipoPrecoAgricolaNome;

	private Long operacaoInternaId;
	private String operacaoInternaNome;

	private Long grupoOperacaoAgricolaId;
	private String grupoOperacaoAgricolaNome;

	private Long finalidadeContratoAgricolaId;
	private String finalidadeContratoAgricolaNome;

	private Long predefinicaoPrecoAgricolaId;
	private String predefinicaoPrecoAgricolaNome;

	private Long safraId;
	private String safraNome;

	private BigDecimal quantidadeContratada;
	private BigDecimal valorUnitarioLiquido;
	private BigDecimal valorUnitarioBruto;
	private BigDecimal valorBruto;
	private BigDecimal valorDesconto;
	private BigDecimal valorLiquido;
	private LocalDate dataDocumento;
	private LocalDate dataMovimento;
	private BigDecimal quantidadeBaixada;
	private BigDecimal valorBaixado;
	private LocalDate dataVencimento;
	private LocalDate dataLimiteEntrega;

	private Long regraAtividadeId;
	private String regraAtividadeNome;

	private BigDecimal valorUnitarioBase;
	private BigDecimal valorPremio;
	private BigDecimal quantidadeAcordoPremio;
	private String observacao;
	private BigDecimal nivelClass1;
	private BigDecimal nivelClass2;
	private BigDecimal nivelClass3;
	private BigDecimal nivelClass4;
}

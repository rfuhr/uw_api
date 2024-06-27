package br.com.ultraworks.erp.api.financeiro.domain.movimentocaixabanco;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MovimentoCaixaBancoDTO {

	private Long id;
	private Long nossoNumero;
	private Long operacaoCaixaBancoId;
	private Long contaId;
	private Long contaTransfId;
	private LocalDate dataMovimento;
	private String numeroDocumento;
	private LocalDate dataMovimentoBanco;
	private LocalDate dataVencimento;
	private Long parceiroLocalId;
	private Long fatoGeradorId;
	private Long historicoPadraoId;
	private String complementoHP;
	private BigDecimal valor;
	private String indicadorDC;
	private boolean compensado;
	private LocalDate dataCompensado;
	private Long grupoFinanceiroId;
	private Long departamentoId;
	
	private String departamentoNome;
	private String bancoNome;
	private String agenciaNome;
	private String contaNome;
	private String contaNumero;
}

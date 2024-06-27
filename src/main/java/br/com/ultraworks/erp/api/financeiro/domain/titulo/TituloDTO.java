package br.com.ultraworks.erp.api.financeiro.domain.titulo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import br.com.ultraworks.erp.api.financeiro.domain.parcela.ParcelaFinanceiroDTO;
import lombok.Data;

@Data
public class TituloDTO {

	private Long id;
	private Long tipoTituloId;
	private String tipoTituloNome;
	private Long nossoNumero;
	private LocalDate dataMovimento;
	private Long empresaFilialId;
	private String empresaFilialNome;
	private Long departamentoId;
	private String departamentoNome;
	private Long parceiroId;
	private String parceiroNome;
	private Long parceiroLocalId;
	private String parceiroLocalNome;
	private Long caracteristicaMovimentoFinanceiroId;
	private String caracteristicaMovimentoFinanceiroNome;
	private Long grupoFinanceiroId;
	private String grupoFinanceiroNome;
	private Long fatoGeradorId;
	private String fatoGeradorNome;
	private Long historicoPadraoId;
	private String historicoPadraoNome;
	private String documento;
	private LocalDate dataDocumento;
	private String complemento;
	private BigDecimal valorTitulo;
	private String observacao;
	private Long negociacaoFinanceiraId;
	
	private List<ParcelaFinanceiroDTO> parcelas;
}

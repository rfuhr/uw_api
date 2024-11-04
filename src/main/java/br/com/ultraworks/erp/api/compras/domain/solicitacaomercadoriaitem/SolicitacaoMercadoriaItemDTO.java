package br.com.ultraworks.erp.api.compras.domain.solicitacaomercadoriaitem;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class SolicitacaoMercadoriaItemDTO {

	private Long id;
	private Long solicitacaoMercadoriaId;
	private Long itemId;
	private Long itemSimplificadoId;
	private Long departamentoEntregaId;
	private BigDecimal quantidadeSolicitada;
	private BigDecimal quantidadeEnviada;
	private BigDecimal quantidadeCancelada;
	private String observacao;
	private Long usuarioSolicitacaoId;
	private LocalDate dataSolicitacao;
	private Long usuarioAtendenteId;
	private LocalDate dataAtendente;
	private int previsaoDiasUtilizacao;
	private String urgenciaSolicitacaoMercadoria;
	private String status;

	private String solicitacaoMercadoriaNome;
	private String itemNome;
	private String itemSimplificadoNome;
	private String departamentoEntregaNome;
	private String usuarioSolicitacaoNome;
	private String usuarioAtendenteNome;
	private String urgenciaSolicitacaoMercadoriaNome;
	private String statusNome;
}

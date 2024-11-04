package br.com.ultraworks.erp.api.compras.domain.solicitacaomercadoria;

import java.time.LocalDate;
import java.util.List;

import br.com.ultraworks.erp.api.compras.domain.solicitacaomercadoriaitem.SolicitacaoMercadoriaItemDTO;
import lombok.Data;

@Data
public class SolicitacaoMercadoriaDTO {

	private Long id;
	private Long numero;
	private Long departamentoSolicitanteId;
	private Long grupoContabilDestinoId;
	private Long departamentoSolicitadoId;
	private LocalDate dataSolicitacao;
	private String situacaoSolicitacaoMercadoria;
	private String observacao;

	private String empresaSolicitanteNome;
	private String empresaFilialSolicitanteNome;
	private String departamentoSolicitanteNome;
	private String grupoContabilDestinoNome;
	private String empresaSolicitadoNome;
	private String empresaFilialSolicitadoNome;
	private String departamentoSolicitadoNome;
	private String situacaoSolicitacaoMercadoriaNome;

	private List<SolicitacaoMercadoriaItemDTO> itens;
}

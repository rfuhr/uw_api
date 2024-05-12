package br.com.ultraworks.erp.api.estoque.domain.movimentoestoque;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class AtualizaEstoqueRequest {

	private Long empresaFilialId;
	private Long grupoContabilId;
	private Long localEstoqueId;
	private Long operacaoInternaId;
	private String tipoMovimentoEstoque;
	private LocalDate data;
	private String documento;
	private String tipoDocumentoEstoque;
	private Long protocoloDocumento;
	private boolean entrada;

	private List<AtualizaEstoqueItensRequest> listaItens;

}

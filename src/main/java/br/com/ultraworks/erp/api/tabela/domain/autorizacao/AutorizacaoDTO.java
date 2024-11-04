package br.com.ultraworks.erp.api.tabela.domain.autorizacao;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AutorizacaoDTO {

	private Long id;
	private Long documentoOrigemId;
	private String documentoIdentificacao;
	private String tipoAutorizacao;
	private String statusAutorizacao;
	private Long usuarioSolicitanteId;
	private Long usuarioAutorizadorId;
	private LocalDate dataSolicitacao;
	private LocalDate dataAutorizacao;

	private String tipoAutorizacaoNome;
	private String statusAutorizacaoNome;
	private String usuarioSolicitanteNome;
	private String usuarioAutorizadorNome;

}

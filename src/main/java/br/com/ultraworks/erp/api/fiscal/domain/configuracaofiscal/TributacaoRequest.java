package br.com.ultraworks.erp.api.fiscal.domain.configuracaofiscal;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TributacaoRequest {

	private Long departamentoId;
	private Long parceiroLocalEnderecoIdDestino;
	private String indicadorOperacaoValue;
	private Long cfopId;
	private Long itemId;
	private Long operacaoInternaId;
	private LocalDateTime dataBase;
}

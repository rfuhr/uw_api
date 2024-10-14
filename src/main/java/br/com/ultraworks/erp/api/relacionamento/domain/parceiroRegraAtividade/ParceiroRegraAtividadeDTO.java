package br.com.ultraworks.erp.api.relacionamento.domain.parceiroRegraAtividade;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;

@Data
public class ParceiroRegraAtividadeDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Long parceiroId;

	private Long regraAtividadeId;
	private String regraAtividadeNome;
	private LocalDate dataInicioVigencia;
	private LocalDate dataFinalVigencia;	
}

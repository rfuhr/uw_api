package br.com.ultraworks.erp.api.seguranca.domain.autonomia;

import lombok.Data;

@Data
public class AutonomiaDTO {

	private Long id;
	private String nome;
	private String descricao;
	private String tag;
	
	private String grupoAutonomiaNome;
	private Long   grupoAutonomiaId;
}

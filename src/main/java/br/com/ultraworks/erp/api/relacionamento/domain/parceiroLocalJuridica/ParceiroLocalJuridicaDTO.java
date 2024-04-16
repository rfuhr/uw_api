package br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalJuridica;

import java.io.Serializable;
import java.sql.Date;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ParceiroLocalJuridicaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Long parceiroLocalId;
	
	@Size(max = 20)
	private String inscricaoEstadual;
	
	private Date dataFundacao;
	
	private String indicadorIE;
	private String suframa;
}

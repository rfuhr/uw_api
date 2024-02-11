package br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalTipoParceiro;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;

@Data
public class ParceiroLocalTipoParceiroDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Long parceiroLocalId;
	
	
	private Long tipoParceiroId;
	private String tipoParceiroNome;
	
}

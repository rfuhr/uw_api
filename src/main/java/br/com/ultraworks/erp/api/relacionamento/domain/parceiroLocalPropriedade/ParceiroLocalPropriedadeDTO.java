package br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocalPropriedade;

import java.io.Serializable;

import lombok.Data;

@Data
public class ParceiroLocalPropriedadeDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Long parceiroLocalId;

	private String identificacao;

}

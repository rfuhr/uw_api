package br.com.ultraworks.erp.api.comercial.domain.configmarkupitemindice;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class ConfigMarkupItemIndiceDTO {

	private Long id;
	private Long configMarkupItemId;
	private Long indiceMarkupId;
	private String indiceMarkupNome;
	private int indiceMarkupCodigo;
	
	private LocalDate dataInicioVigencia;
	private LocalDate dataFinalVigencia;
	private BigDecimal percentual;
}

package br.com.ultraworks.erp.api.comercial.domain.configmarkupplanoitemindice;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class ConfigMarkupPlanoItemIndiceDTO {

	private Long id;
	private Long configMarkupPlanoItemId;
	private Long indiceMarkupId;
	private String indiceMarkupNome;
	private int indiceMarkupCodigo;
	
	private LocalDate dataInicioVigencia;
	private LocalDate dataFinalVigencia;
	private BigDecimal percentual;
}

package br.com.ultraworks.erp.api.agricola.integrator.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RomaneioProblemaFixacaoDTO {

	private Long romaneioAgricolaId;
	private Long romaneioAgricolaNumero;
	private String romaneioAgricolaData;
	private BigDecimal romaneioAgricolaSaldoFixar;
	private String problema;
}

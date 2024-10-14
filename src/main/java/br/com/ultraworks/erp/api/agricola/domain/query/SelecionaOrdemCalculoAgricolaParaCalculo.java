package br.com.ultraworks.erp.api.agricola.domain.query;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SelecionaOrdemCalculoAgricolaParaCalculo {

	private String indetificacaoDocumentoAgricola;
	private int ordem;
	private Long tipoCalculoAgricolaId;
	private LocalDate dataInicioVigencia;
	private String baseCalculoAgricola;
	private String idnDC;
}

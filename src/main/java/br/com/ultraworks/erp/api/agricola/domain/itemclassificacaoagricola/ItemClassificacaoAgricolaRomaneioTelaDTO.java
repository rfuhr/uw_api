package br.com.ultraworks.erp.api.agricola.domain.itemclassificacaoagricola;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemClassificacaoAgricolaRomaneioTelaDTO {

	private Long id;
	private Long grupoClassificacaoAgricolaId;
	private Long codigo;
	private String nome;
	private Long tipoCalculoAgricolaIdRomaneio;
	private int ordem;

	private String grupoClassificacaoAgricolaNome;
	private String tipoCalculoAgricolaNomeRomaneio;
}

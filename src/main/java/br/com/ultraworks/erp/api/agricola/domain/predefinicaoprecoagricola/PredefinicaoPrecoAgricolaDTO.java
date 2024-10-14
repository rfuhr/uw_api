package br.com.ultraworks.erp.api.agricola.domain.predefinicaoprecoagricola;

import java.time.LocalDate;

import br.com.ultraworks.erp.core.annotation.FriendlyName;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PredefinicaoPrecoAgricolaDTO {

	private Long id;
	@NotNull
	private Long codigo;
	
	@NotNull
	@NotEmpty
	@Size(max = 80)
	@FriendlyName("Nome")
	private String nome;
	 
	private Long itemClassificacaoAgricola1Id;
	private Long itemClassificacaoAgricola2Id;
	private Long itemClassificacaoAgricola3Id;
	private Long itemClassificacaoAgricola4Id;
	
	@NotNull
	private LocalDate dataInicioVigencia;
	@NotNull
	private LocalDate dataFinalVigencia;
	
	private String itemClassificacaoAgricola1Nome;
	private String itemClassificacaoAgricola2Nome;
	private String itemClassificacaoAgricola3Nome;
	private String itemClassificacaoAgricola4Nome;
	
	private Long itemClassificacaoAgricola1Codigo;
	private Long itemClassificacaoAgricola2Codigo;
	private Long itemClassificacaoAgricola3Codigo;
	private Long itemClassificacaoAgricola4Codigo;	
}

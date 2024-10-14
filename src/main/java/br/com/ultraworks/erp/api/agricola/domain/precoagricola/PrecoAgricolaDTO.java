package br.com.ultraworks.erp.api.agricola.domain.precoagricola;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PrecoAgricolaDTO {

	private Long id;
	@NotNull
	private Long itemId;
	@NotNull
	private Long tipoPrecoAgricolaId;
	private Long empresaId;
	private Long empresaFilialId;
	private Long departamentoId;
	@NotNull
	private Long predefinicaoPrecoAgricolaId;

	private Long nivelClass1Id;
	private Long nivelClass2Id;
	private Long nivelClass3Id;
	private Long nivelClass4Id;

	@NotNull
	private BigDecimal valorUnitario;

	@NotNull
	private LocalDate dataInicioVigencia;
	@NotNull
	private LocalDate dataFinalVigencia;

	private String itemNome;
	private String tipoPrecoAgricolaNome;
	private String empresaNome;
	private String empresaFilialNome;
	private String departamentoNome;
	private String predefinicaoPrecoAgricolaNome;
}

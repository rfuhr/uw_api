package br.com.ultraworks.erp.api.agricola.domain.precoagricola;

import java.time.LocalDate;

import lombok.Data;

@Data
public class BuscaPrecoAgricolaRequest {

	private Long itemId;
	private Long tipoPrecoAgricolaId;
	private Long empresaId;
	private Long empresaFilialId;
	private Long departamentoId;
	private Long predefinicaoPrecoAgricolaId;
	private Long nivelClass1Id;
	private Long nivelClass2Id;
	private Long nivelClass3Id;
	private Long nivelClass4Id;
	private LocalDate dataBase;

}

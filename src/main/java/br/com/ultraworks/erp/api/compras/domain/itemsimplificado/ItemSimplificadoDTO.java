package br.com.ultraworks.erp.api.compras.domain.itemsimplificado;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ItemSimplificadoDTO {

	private Long id;
	private int codigo;
	private String nome;
	private LocalDate dataInicioVigencia;
	private LocalDate dataFinalVigencia;
}

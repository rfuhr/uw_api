package br.com.ultraworks.erp.core.dto;

import java.util.Collections;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResultPage<E> {
	
	private List<E> registros;
	private long totalRegistros;
	private int paginaAtual;
	private int tamanhoPagina;
	private int totalPaginas;

	public static <E> ResultPage<E> empty() {
        return new ResultPage<>(Collections.emptyList(), 0, 0, 0, 0);
    }
}

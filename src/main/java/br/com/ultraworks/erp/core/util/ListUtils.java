package br.com.ultraworks.erp.core.util;

import java.util.List;
import java.util.stream.Collectors;

public class ListUtils {
	
	public static <T> List<T> compararListasERetornaDiferenca(List<T> listaA, List<T> listaB) {
		if (listaA.size() > 0 && listaB.size() > 0) {
			return listaA.stream().filter(f -> !listaB.contains(f)).collect(Collectors.toList());
		}
		return listaA;
	}

}

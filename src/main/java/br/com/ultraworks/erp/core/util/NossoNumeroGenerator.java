package br.com.ultraworks.erp.core.util;

public class NossoNumeroGenerator {

	private static final int PROCESS_IDENTIFIER = (int) (Math.random() * 1000);

	public static Long gerarNossoNumero() {
		long timestamp = System.nanoTime();
		// Obtém o ID do thread atual
		long threadIdentifier = Thread.currentThread().getId();
		// Combina os valores para formar um número único
		return timestamp + PROCESS_IDENTIFIER + threadIdentifier;
	}
}

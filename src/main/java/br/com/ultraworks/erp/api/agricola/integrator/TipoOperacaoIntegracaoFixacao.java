package br.com.ultraworks.erp.api.agricola.integrator;

public enum TipoOperacaoIntegracaoFixacao {
	SELECAOROMANEIOS,
	FIXARROMANEIOS;

	public boolean isSelecaoRomaneios() {
		return this == SELECAOROMANEIOS;
	}
	
	public boolean isFixarRomaneios() {
		return this == FIXARROMANEIOS;
	}
}

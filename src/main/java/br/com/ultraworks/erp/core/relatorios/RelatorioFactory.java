package br.com.ultraworks.erp.core.relatorios;

public interface RelatorioFactory<T extends TipoRelatorio> {
    RelatorioStrategy getStrategy(T tipoRelatorio);
}
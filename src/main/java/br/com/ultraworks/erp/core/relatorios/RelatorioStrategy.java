package br.com.ultraworks.erp.core.relatorios;

import br.com.ultraworks.erp.core.dto.ReportGenerated;

public interface RelatorioStrategy {
	
	ReportGenerated imprimirRelatorio(ISelecaoRelatorioRequest request);
}

package br.com.ultraworks.erp.api.financeiro.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.financeiro.domain.relatorio.TipoRelatorioFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.relatorio.request.SelecaoRelatorioFinanceiroRequest;
import br.com.ultraworks.erp.core.dto.ReportGenerated;
import br.com.ultraworks.erp.core.relatorios.RelatorioStrategy;

@Service
public class RelatoriosFinanceiroService {

    private final RelatorioFinanceiroFactory relatorioFinanceiroFactory;

    @Autowired
    public RelatoriosFinanceiroService(RelatorioFinanceiroFactory relatorioFinanceiroFactory) {
        this.relatorioFinanceiroFactory = relatorioFinanceiroFactory;

    }

    public ReportGenerated imprimirRelatorioFinanceiro(SelecaoRelatorioFinanceiroRequest request) {
        TipoRelatorioFinanceiro tipoRelatorio = TipoRelatorioFinanceiro.fromValue(request.getTipoRelatorioFinanceiro());
        RelatorioStrategy strategy = relatorioFinanceiroFactory.getStrategy(tipoRelatorio);
        if (strategy != null) {
            return strategy.imprimirRelatorio(request);
        }
        return null;
    }


}

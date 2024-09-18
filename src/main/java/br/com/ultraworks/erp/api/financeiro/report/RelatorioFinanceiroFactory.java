package br.com.ultraworks.erp.api.financeiro.report;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.financeiro.domain.relatorio.TipoRelatorioFinanceiro;
import br.com.ultraworks.erp.core.relatorios.RelatorioFactory;
import br.com.ultraworks.erp.core.relatorios.RelatorioStrategy;

@Service
public class RelatorioFinanceiroFactory implements RelatorioFactory<TipoRelatorioFinanceiro> {

    private final Map<TipoRelatorioFinanceiro, RelatorioStrategy> strategies;

    @Autowired
    public RelatorioFinanceiroFactory(List<RelatorioStrategy> strategyList) {
        strategies = strategyList.stream().collect(Collectors.toMap(
            strategy -> TipoRelatorioFinanceiro.valueOf(strategy.getClass().getSimpleName().replace("Strategy", "").toUpperCase()),
            strategy -> strategy
        ));
    }

    @Override
    public RelatorioStrategy getStrategy(TipoRelatorioFinanceiro tipoRelatorio) {
        return strategies.get(tipoRelatorio);
    }
}


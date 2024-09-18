package br.com.ultraworks.erp.api.financeiro.report;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.financeiro.domain.caracteristicamovfin.CaracteristicaMovimentoFinanceiro;
import br.com.ultraworks.erp.api.financeiro.domain.carteirafinanceira.CarteiraFinanceira;
import br.com.ultraworks.erp.api.financeiro.domain.relatorio.request.SelecaoRelatorioFinanceiroRequest;
import br.com.ultraworks.erp.api.financeiro.domain.tipotitulo.TipoTitulo;
import br.com.ultraworks.erp.api.financeiro.service.CaracteristicaMovimentoFinanceiroService;
import br.com.ultraworks.erp.api.financeiro.service.CarteiraFinanceiraService;
import br.com.ultraworks.erp.api.financeiro.service.TipoTituloService;
import br.com.ultraworks.erp.api.organograma.domain.departamento.Departamento;
import br.com.ultraworks.erp.api.organograma.domain.empresa.Empresa;
import br.com.ultraworks.erp.api.organograma.service.DepartamentoService;
import br.com.ultraworks.erp.api.organograma.service.EmpresaService;
import br.com.ultraworks.erp.api.relacionamento.domain.parceiroLocal.ParceiroLocal;
import br.com.ultraworks.erp.api.relacionamento.service.ParceiroLocalService;
import br.com.ultraworks.erp.core.relatorios.AbstractRelatorioStrategy;
import br.com.ultraworks.erp.core.relatorios.ISelecaoRelatorioRequest;
import br.com.ultraworks.erp.core.security.domain.CustomUser;
import br.com.ultraworks.erp.core.service.ReportsService;
import br.com.ultraworks.erp.core.util.DateUtils;
import br.com.ultraworks.erp.core.util.SQLUtils;

@Service
public class AnaliticoAdtoDevAbertoStrategy extends AbstractRelatorioStrategy {

    private final TipoTituloService tipoTituloService;
    private final DepartamentoService departamentoService;
    private final CaracteristicaMovimentoFinanceiroService caracteristicaMovimentoFinanceiroService;
    private final ParceiroLocalService parceiroLocalService;
    private final CarteiraFinanceiraService carteiraFinanceiraService;

    @Autowired
    public AnaliticoAdtoDevAbertoStrategy(ReportsService reportsService, EmpresaService empresaService,
                                       TipoTituloService tipoTituloService, DepartamentoService departamentoService,
                                       CaracteristicaMovimentoFinanceiroService caracteristicaMovimentoFinanceiroService,
                                       ParceiroLocalService parceiroLocalService, CarteiraFinanceiraService carteiraFinanceiraService) {
        super(reportsService, empresaService);
        this.tipoTituloService = tipoTituloService;
        this.departamentoService = departamentoService;
        this.caracteristicaMovimentoFinanceiroService = caracteristicaMovimentoFinanceiroService;
        this.parceiroLocalService = parceiroLocalService;
        this.carteiraFinanceiraService = carteiraFinanceiraService;
    }

    @Override
    protected void addSpecificParameters(Map<String, Object> parameters, ISelecaoRelatorioRequest request) {
        SelecaoRelatorioFinanceiroRequest financeiroRequest = (SelecaoRelatorioFinanceiroRequest) request;
        Date dataVencimentoInicio = Date.valueOf(financeiroRequest.getVencimentoInicial());
        Date dataVencimentoFinal = Date.valueOf(financeiroRequest.getVencimentoFinal());
        Date competencia = Date.valueOf(financeiroRequest.getCompetencia());
        String filtroTipoTitulo = getFiltroTipoTitulo(financeiroRequest.getTipoTituloId());
        String filtroDepartamento = getFiltroDepartamento(financeiroRequest.getDepartamentoId());
        String filtroCaractMovto = getFiltroCaractMovto(financeiroRequest.getCaracteristicaMovimentoFinanceiroId());
        String filtroParceiro = getFiltroParceiro(financeiroRequest.getParceiroLocalId());
        String filtroCarteira = getFiltroCarteira(financeiroRequest.getCarteiraFinanceiraId());

        parameters.put("dataVectoInicio", dataVencimentoInicio);
        parameters.put("dataVectoFinal", dataVencimentoFinal);
        parameters.put("competencia", competencia);
        parameters.put("filtroTipoTitulo", filtroTipoTitulo);
        parameters.put("filtroDepartamento", filtroDepartamento);
        parameters.put("filtroCaractMovto", filtroCaractMovto);
        parameters.put("filtroParceiro", filtroParceiro);
        parameters.put("filtroCarteira", filtroCarteira);
    }

    private String getFiltroTipoTitulo(Long tipoTituloId) {
        if (tipoTituloId != null) {
            TipoTitulo tipoTitulo = tipoTituloService.getById(tipoTituloId).get();
            return String.valueOf(tipoTitulo.getCodigo());
        }
        return "<TODOS>";
    }

    private String getFiltroDepartamento(Long departamentoId) {
        if (departamentoId != null) {
            Departamento departamento = departamentoService.getById(departamentoId).get();
            return departamento.getSigla();
        }
        return "<TODOS>";
    }

    private String getFiltroCaractMovto(Long caracteristicaMovimentoFinanceiroId) {
        if (caracteristicaMovimentoFinanceiroId != null) {
            CaracteristicaMovimentoFinanceiro caracteristicaMovimentoFinanceiro = caracteristicaMovimentoFinanceiroService.getById(caracteristicaMovimentoFinanceiroId).get();
            return String.valueOf(caracteristicaMovimentoFinanceiro.getCodigo());
        }
        return "<TODAS>";
    }

    private String getFiltroParceiro(Long parceiroLocalId) {
        if (parceiroLocalId != null) {
            ParceiroLocal parceiroLocal = parceiroLocalService.getById(parceiroLocalId).get();
            return parceiroLocal.getParceiro().getRaizCnpjCpf();
        }
        return "<TODOS>";
    }

    private String getFiltroCarteira(Long carteiraFinanceiraId) {
        if (carteiraFinanceiraId != null) {
            CarteiraFinanceira carteiraFinanceira = carteiraFinanceiraService.getById(carteiraFinanceiraId).get();
            return String.valueOf(carteiraFinanceira.getCodigo());
        }
        return "<TODAS>";
    }

    @Override
    protected String getReportName() {
        return "financeiro/AnaliticoAdtoDevAberto";
    }
    
    @Override
    protected String getSqlWhere(ISelecaoRelatorioRequest request) {
    	SelecaoRelatorioFinanceiroRequest financeiroRequest = (SelecaoRelatorioFinanceiroRequest) request;
        CustomUser user = getAuthenticatedUser();
        Optional<Empresa> empresa = getEmpresa(user.getEmpresaId());
        
    	Map<String, String> where = new HashMap<>();
    	where.put("empresaId", String.valueOf(empresa.get().getId()));
    	if (financeiroRequest.getTipoTituloId() != null) 
    		where.put("tipotituloid", String.valueOf(financeiroRequest.getTipoTituloId()));
    	if (financeiroRequest.getDepartamentoId() != null)
    		where.put("departamentoid", String.valueOf(financeiroRequest.getDepartamentoId()));
    	if (financeiroRequest.getParceiroLocalId() != null) {
    		ParceiroLocal parceiroLocal = parceiroLocalService.getById(financeiroRequest.getParceiroLocalId()).get();
    		where.put("parceiroId", String.valueOf(parceiroLocal.getParceiro().getId()));
    	}
    	if (financeiroRequest.getCaracteristicaMovimentoFinanceiroId() != null) {
    		where.put("caracteristicamovimentofinanceiroid", String.valueOf(financeiroRequest.getCaracteristicaMovimentoFinanceiroId()));
    	}
    	if (financeiroRequest.getCarteiraFinanceiraId() != null) {
    		where.put("carteiraid", String.valueOf(financeiroRequest.getCarteiraFinanceiraId()));
    	}
    	String sql = SQLUtils.generateWhereClause(where);
    	sql = sql.concat(" AND parcelafinanceiradatavencimento between '").concat(DateUtils.formatDate_yyyyMMdd(financeiroRequest.getVencimentoInicial()))
    			.concat("' AND '").concat(DateUtils.formatDate_yyyyMMdd(financeiroRequest.getVencimentoFinal())).concat("'");
        return sql;
    }
    
    @Override
    protected String getSql(ISelecaoRelatorioRequest request) {
    	SelecaoRelatorioFinanceiroRequest financeiroRequest = (SelecaoRelatorioFinanceiroRequest) request;
    	if (financeiroRequest.getCompetencia().isBefore(LocalDate.now())) {
    		return "select * from FC_FIN_MONTAPOSTITULO_RETRO('" + DateUtils.formatDate_yyyyMMdd(financeiroRequest.getCompetencia()) + "')";
    	} else {
    		return null;
    	}
    }
}


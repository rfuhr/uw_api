package br.com.ultraworks.erp.core.relatorios;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.ultraworks.erp.api.organograma.domain.empresa.Empresa;
import br.com.ultraworks.erp.api.organograma.service.EmpresaService;
import br.com.ultraworks.erp.core.dto.ReportGenerated;
import br.com.ultraworks.erp.core.security.domain.CustomUser;
import br.com.ultraworks.erp.core.service.ReportsService;
import net.sf.jasperreports.engine.query.JRJdbcQueryExecuterFactory;

public abstract class AbstractRelatorioStrategy implements RelatorioStrategy {

    protected final ReportsService reportsService;
    protected final EmpresaService empresaService;

    @Autowired
    public AbstractRelatorioStrategy(ReportsService reportsService, EmpresaService empresaService) {
        this.reportsService = reportsService;
        this.empresaService = empresaService;
    }

    protected CustomUser getAuthenticatedUser() {
        return (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    protected Optional<Empresa> getEmpresa(Long empresaId) {
        return empresaService.getById(empresaId);
    }

    protected Map<String, Object> getCommonParameters(CustomUser user, Empresa empresa) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("usuarioNome", user.getUsername());
        parameters.put("empresaNome", empresa.getNome());
        return parameters;
    }

    protected ReportGenerated generateResponse(String reportName, String sql, Map<String, Object> parameters) {
        try {
        	byte[] report = null;
        	if (StringUtils.isBlank(sql))
        		report = reportsService.generateReport(reportName, parameters);
        	else
        		report = reportsService.generateReport(reportName, sql, parameters);
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + reportName + ".pdf");
            
            ReportGenerated reportGenerated = ReportGenerated.builder().nameReport(reportName + ".pdf").bytes(report).build();
            return reportGenerated;
        } catch (Exception e) {
                  return null;
        }
    }

    @Override
    public ReportGenerated imprimirRelatorio(ISelecaoRelatorioRequest request) {
        CustomUser user = getAuthenticatedUser();
        Optional<Empresa> empresa = getEmpresa(user.getEmpresaId());
        if (empresa.isPresent()) {
            Map<String, Object> parameters = getCommonParameters(user, empresa.get());
            addSpecificParameters(parameters, request);
            String sql = getSql(request);

            String sqlWhere = getSqlWhere(request);
            if (StringUtils.isNotBlank(sqlWhere))
            	parameters.put("sqlWhere", sqlWhere);
            
            return generateResponse(getReportName(), sql, parameters);
        }
        return null;
    }

    protected abstract void addSpecificParameters(Map<String, Object> parameters, ISelecaoRelatorioRequest request);

    protected abstract String getReportName();
    
    protected abstract String getSqlWhere(ISelecaoRelatorioRequest request);
    
    protected abstract String getSql(ISelecaoRelatorioRequest request);
}
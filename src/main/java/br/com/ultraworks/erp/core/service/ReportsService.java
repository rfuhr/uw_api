package br.com.ultraworks.erp.core.service;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.core.multitenancy.config.tenant.hibernate.DynamicDataSourceBasedMultiTenantConnectionProvider;
import br.com.ultraworks.erp.core.multitenancy.util.TenantContext;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Service
public class ReportsService {

	@Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private DynamicDataSourceBasedMultiTenantConnectionProvider dy;

    public byte[] generateReport(String reportTemplate, Map<String, Object> parameters) throws Exception {
        Resource resource = resourceLoader.getResource("classpath:reports/" + reportTemplate + ".jrxml");
        InputStream inputStream = resource.getInputStream();
        
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

        try (Connection connection = dy.getConnection(TenantContext.getTenantId())) {
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
            return JasperExportManager.exportReportToPdf(jasperPrint);
        }
    }
    
}
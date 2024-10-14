package br.com.ultraworks.erp.core.service;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@Service
public class ReportsService {

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	private DynamicDataSourceBasedMultiTenantConnectionProvider dy;

	public byte[] generateReport(String reportTemplate, Map<String, Object> parameters) throws Exception {
		Resource resource = resourceLoader.getResource("classpath:reports/" + reportTemplate + ".jrxml");
		if (resource.exists()) {
			InputStream inputStream = resource.getInputStream();

			JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

			try (Connection connection = dy.getConnection(TenantContext.getTenantId())) {
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
				return JasperExportManager.exportReportToPdf(jasperPrint);
			}
		} else {
			throw new RuntimeException("Não encontrado o template do relatório");
		}
	}

	public byte[] generateReport(String reportTemplate, String sqlQuery, Map<String, Object> parameters)
			throws Exception {
		Resource resource = resourceLoader.getResource("classpath:reports/" + reportTemplate + ".jrxml");
		InputStream inputStream = resource.getInputStream();

		JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
		JRDesignQuery newQuery = new JRDesignQuery();
		newQuery.setText(sqlQuery);
		jasperDesign.setQuery(newQuery);

		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

		try (Connection connection = dy.getConnection(TenantContext.getTenantId())) {
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
			return JasperExportManager.exportReportToPdf(jasperPrint);
		}
	}

}

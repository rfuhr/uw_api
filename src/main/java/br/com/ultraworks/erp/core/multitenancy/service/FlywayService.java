package br.com.ultraworks.erp.core.multitenancy.service;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.ClassicConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import br.com.ultraworks.erp.core.multitenancy.config.TenantConfigs;
import br.com.ultraworks.erp.core.multitenancy.config.tenant.hibernate.DynamicDataSourceBasedMultiTenantConnectionProvider;
import br.com.ultraworks.erp.core.multitenancy.domain.entity.Tenant;

@Component
public class FlywayService {

	private final Flyway flyway;
	private DynamicDataSourceBasedMultiTenantConnectionProvider dProvider;

	@Autowired
	public FlywayService(Flyway flyway, DynamicDataSourceBasedMultiTenantConnectionProvider dProvider) {
		super();
		this.flyway = flyway;
		this.dProvider = dProvider;
	}

	public void checkMigration() {
		ClassicConfiguration configuration = ((ClassicConfiguration) flyway.getConfiguration());
		configuration.setSqlMigrationPrefix("UW");
		TenantConfigs.getConfigs().forEach(clientConfig -> {
			DataSource dataSource = dProvider.selectDataSource(clientConfig.getTenantId());
			configuration.setDataSource(dataSource);
			Flyway f = new Flyway(configuration);
			f.migrate();
		});

	}
}

package br.com.ultraworks.erp.core.multitenancy.config.tenant.hibernate;


import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.stereotype.Component;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.github.benmanes.caffeine.cache.RemovalListener;
import com.zaxxer.hikari.HikariDataSource;

import br.com.ultraworks.erp.core.multitenancy.domain.entity.Tenant;
import br.com.ultraworks.erp.core.multitenancy.repository.TenantRepository;
import br.com.ultraworks.erp.core.util.EncryptionService;
import jakarta.annotation.PostConstruct;

@Component
public class DynamicDataSourceBasedMultiTenantConnectionProvider
        extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {

    private static final long serialVersionUID = -460277105706399638L;

    private static final String TENANT_POOL_NAME_SUFFIX = "DataSource";

    private final DataSource masterDataSource;

    private final DataSourceProperties dataSourceProperties;

    private final TenantRepository masterTenantRepository;
    
    @Value("${multitenancy.tenant.datasource.url-prefix}")
    private String urlPrefix;

    @Value("${multitenancy.datasource-cache.maximumSize:100}")
    private Long maximumSize;

    @Value("${multitenancy.datasource-cache.expireAfterAccess:10}")
    private Integer expireAfterAccess;

    @Value("${encryption.secret}")
    private String secret;

    @Value("${encryption.salt}")
    private String salt;

    private LoadingCache<String, DataSource> tenantDataSources;
    
    
    @Autowired
    public DynamicDataSourceBasedMultiTenantConnectionProvider(EncryptionService encryptionService, TenantRepository masterTenantRepository,
    		@Qualifier("masterDataSource")  DataSource masterDataSource, @Qualifier("masterDataSourceProperties") DataSourceProperties dataSourceProperties) {
		this.masterTenantRepository = masterTenantRepository;
		this.masterDataSource = masterDataSource;
		this.dataSourceProperties = dataSourceProperties;
	}

	@PostConstruct
    private void createCache() {
        tenantDataSources = Caffeine.newBuilder()
                .maximumSize(maximumSize)
                .expireAfterAccess(expireAfterAccess, TimeUnit.MINUTES)
                .removalListener((RemovalListener<String, DataSource>) (tenantId, dataSource, removalCause) -> {
                    HikariDataSource ds = (HikariDataSource) dataSource;
                    ds.close(); 
                })
                .build(tenantId -> {
                        Tenant tenant = masterTenantRepository.findByTenantId(tenantId)
                                .orElseThrow(() -> new RuntimeException("No such tenant: " + tenantId));
                        return createAndConfigureDataSource(tenant);
                    }
                );
    }

    @Override
    protected DataSource selectAnyDataSource() {
        return masterDataSource;
    }

    @Override
	public DataSource selectDataSource(String tenantIdentifier) {
        return tenantDataSources.get(tenantIdentifier);
    }

    private DataSource createAndConfigureDataSource(Tenant tenant) {
        //String decryptedPassword = encryptionService.decrypt(tenant.getPassword(), secret, salt);
        String decryptedPassword = tenant.getPassword();
        HikariDataSource ds = dataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();

        ds.setUsername(tenant.getUsername());
        ds.setPassword(decryptedPassword);
        ds.setJdbcUrl(urlPrefix + tenant.getDb());

        ds.setPoolName(tenant.getTenantId() + TENANT_POOL_NAME_SUFFIX);

        return ds;
    }

}
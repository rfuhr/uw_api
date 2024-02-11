package br.com.ultraworks.erp.core.multitenancy.service;

import org.springframework.data.repository.query.Param;

import br.com.ultraworks.erp.core.multitenancy.domain.entity.Tenant;

public interface TenantService {
	
	Tenant findByTenantId(@Param("tenantId") String tenantId);
}

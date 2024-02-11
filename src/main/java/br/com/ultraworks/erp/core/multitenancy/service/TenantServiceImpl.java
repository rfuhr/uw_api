package br.com.ultraworks.erp.core.multitenancy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.core.multitenancy.domain.entity.Tenant;
import br.com.ultraworks.erp.core.multitenancy.repository.TenantRepository;

@Service
public class TenantServiceImpl implements TenantService {

    private final TenantRepository tenantRepository;

    @Autowired
    public TenantServiceImpl(TenantRepository tenantRepository) {
		this.tenantRepository = tenantRepository;
	}


	@Override
    public Tenant findByTenantId(String tenantId) {
        return tenantRepository.findByTenantId(tenantId)
                .orElseThrow(() -> new RuntimeException("No such tenant: " + tenantId));
    }


}

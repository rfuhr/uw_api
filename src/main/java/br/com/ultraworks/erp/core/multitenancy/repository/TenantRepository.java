package br.com.ultraworks.erp.core.multitenancy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.ultraworks.erp.core.multitenancy.domain.entity.Tenant;

public interface TenantRepository extends JpaRepository<Tenant, String> {

    @Query("select t from Tenant t where t.tenantId = :tenantId")
    Optional<Tenant> findByTenantId(@Param("tenantId") String tenantId);
}
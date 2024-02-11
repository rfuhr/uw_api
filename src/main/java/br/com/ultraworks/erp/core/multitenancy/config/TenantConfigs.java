package br.com.ultraworks.erp.core.multitenancy.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.ultraworks.erp.core.multitenancy.domain.entity.Tenant;

public class TenantConfigs {

	private static List<Tenant> configs = new ArrayList<>();

    public static List<Tenant> getConfigs() {
        return configs;
    }

    public static void setConfigs(List<Tenant> configs) {
        TenantConfigs.configs = configs;
    }

    public static Tenant findClient(String tenant) {
        return configs.stream().filter(config -> config.getTenantId().equals(tenant)).findFirst().orElse(null);
    }

    public static Optional<Tenant> findOptionalClient(String tenant) {
        return configs.stream().filter(config -> config.getTenantId().equals(tenant)).findFirst();
    }

    public static void addClient(Tenant config) {
        configs.add(config);
    }
    
}

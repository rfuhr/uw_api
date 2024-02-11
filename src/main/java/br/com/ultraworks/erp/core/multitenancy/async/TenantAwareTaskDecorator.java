package br.com.ultraworks.erp.core.multitenancy.async;

import org.springframework.core.task.TaskDecorator;
import org.springframework.lang.NonNull;

import br.com.ultraworks.erp.core.multitenancy.util.TenantContext;

public class TenantAwareTaskDecorator implements TaskDecorator {

    @Override
    @NonNull
    public Runnable decorate(@NonNull Runnable runnable) {
        String tenantId = TenantContext.getTenantId();
        return () -> {
            try {
                TenantContext.setTenantId(tenantId);
                runnable.run();
            } finally {
                TenantContext.setTenantId(null);
            }
        };
    }
}
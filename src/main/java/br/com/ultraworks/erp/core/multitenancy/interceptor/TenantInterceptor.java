package br.com.ultraworks.erp.core.multitenancy.interceptor;

import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

import br.com.ultraworks.erp.core.multitenancy.util.TenantContext;

@Component
@Order(0)
public class TenantInterceptor implements WebRequestInterceptor {

    public static final String X_TENANT_ID = "X-Tenant-Id";

    @Override
    public void preHandle(WebRequest request) {
        if (request.getHeader(X_TENANT_ID) != null) {
            String tenantId = request.getHeader(X_TENANT_ID);
            TenantContext.setTenantId(tenantId);
        }
    }

    @Override
    public void postHandle(@NonNull WebRequest request, ModelMap model) {
        TenantContext.clear();
    }

    @Override
    public void afterCompletion(@NonNull WebRequest request, Exception ex) {
    }
}

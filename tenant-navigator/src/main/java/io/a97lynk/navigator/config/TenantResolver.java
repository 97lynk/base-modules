package io.a97lynk.navigator.config;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

@Component
public class TenantResolver implements CurrentTenantIdentifierResolver {

    private String defaultTenant = "public";

    @Override
    public String resolveCurrentTenantIdentifier() {
        String t = TenantContext.getCurrentTenant();
        if (t != null) {
            return t;
        } else {
            return defaultTenant;
        }
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}

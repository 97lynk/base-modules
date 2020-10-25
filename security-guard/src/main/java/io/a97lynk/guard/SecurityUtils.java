package io.a97lynk.guard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {

    @Autowired
    JwtAccessTokenConverter jwtAccessTokenConverter;

    @Bean
    public SecurityContext securityContext(){
        return SecurityContextHolder.getContext();
    }
}

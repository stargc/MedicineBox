package com.zhiyi.medicinebox.api.configuration;

import com.zhiyi.medicinebox.api.business.common.security.TokenAuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {

    // 需要认证的URL
    private static final String[] AUTH_NEEDED_URLS = {
            "/test1",
    };

    // TOKEN认证filter
    @Bean
    public FilterRegistrationBean tokenCheckFilter(TokenAuthenticationFilter filter){
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        registration.addUrlPatterns(AUTH_NEEDED_URLS);
        return registration;
    }
}

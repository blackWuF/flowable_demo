package com.wuf.flowable.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @Author wuf
 * @Description
 * @PackageName flowable_demo
 * @Package com.wuf.flowable.config
 * @Date 2023/8/4 11:06
 */
@Configuration
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {
        http.csrf().disable();
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin();
        return http.build();
    }
}


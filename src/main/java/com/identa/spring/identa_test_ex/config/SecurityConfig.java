package com.identa.spring.identa_test_ex.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
    @Autowired
    DataSource dataSource;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(){
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
        http.formLogin();
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers(mvcMatcherBuilder.pattern("/orders")).hasRole("EMPLOYEE")
                .requestMatchers(mvcMatcherBuilder.pattern("/updateInfo")).hasRole("EMPLOYEE")
                .requestMatchers(mvcMatcherBuilder.pattern("/deleteOrder")).hasRole("EMPLOYEE")
                .requestMatchers(mvcMatcherBuilder.pattern("/confirmOrder")).hasRole("EMPLOYEE")
                .requestMatchers(mvcMatcherBuilder.pattern("/saveAfterUpdateOrder")).hasRole("EMPLOYEE")
                .anyRequest().authenticated()
        );
        return http.build();
    }
}

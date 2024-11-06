package com.example.rolebasedaccess.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.example.rolebasedaccess.UserRole;

@EnableWebSecurity
public class SecurityConfig {
	
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers(HttpMethod.GET, "/v1/entity/**").hasAnyAuthority(UserRole.ANALYST.name(), UserRole.MANAGER.name())
                        .requestMatchers(HttpMethod.POST, "/v1/entity/**").hasAuthority(UserRole.MANAGER.name())
                        .requestMatchers(HttpMethod.PUT, "/v1/entity/**").hasAuthority(UserRole.MANAGER.name())
                        .requestMatchers(HttpMethod.DELETE, "/v1/entity/**").hasAuthority(UserRole.MANAGER.name())
                        .anyRequest().authenticated()
                );
        return http.build();
    }
    
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/ignore1", "/ignore2");
    }
    
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user1 = User.builder()
                .username("anurag")
                .password("{noop}password")
                .authorities(UserRole.MANAGER.name())
                .build();
        UserDetails user2 = User.builder()
                .username("akash")
                .password("{noop}password")
                .authorities(UserRole.ANALYST.name())
                .build();
        return new InMemoryUserDetailsManager(user1, user2);
    }
}
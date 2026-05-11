package com.gabrielspassos.config.v2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/public/**").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService users() {

        UserDetails analyst = User.builder()
                .username("analyst")
                .password("{noop}password")
                .authorities(
                        "invoice:read",
                        "report:generate"
                )
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}password")
                .authorities(
                        "invoice:read",
                        "invoice:write",
                        "invoice:delete",
                        "report:generate"
                )
                .build();

        return new InMemoryUserDetailsManager(analyst, admin);
    }
}

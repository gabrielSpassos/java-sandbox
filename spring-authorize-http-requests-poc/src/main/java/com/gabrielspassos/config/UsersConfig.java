package com.gabrielspassos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UsersConfig {

    @Bean
    public UserDetailsService users() {
        UserDetails user = User.builder()
                .username("user")
                .password("{noop}password")
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}password")
                //.roles("ADMIN") override by the authorities
                .authorities(
                        "ROLE_ADMIN",
                        "invoice:read",
                        "invoice:write",
                        "invoice:delete",
                        "report:generate"
                )
                .build();

        UserDetails analyst = User.builder()
                .username("analyst")
                .password("{noop}password")
                .authorities(
                        "invoice:read",
                        "report:generate"
                )
                .build();

        return new InMemoryUserDetailsManager(user, admin, analyst);
    }

}

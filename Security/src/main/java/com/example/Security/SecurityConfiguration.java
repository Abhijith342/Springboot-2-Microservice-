package com.example.Security;

import org.springframework.security.core.userdetails.User;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
// import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

@Configuration
// @EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    @Bean
     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 1. Require authentication for all incoming requests
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated()
            )
            // 2. Enable HTTP Basic authentication to trigger the native browser pop-up
            // .formLogin(Customizer.withDefaults())
            .httpBasic(Customizer.withDefaults());

        return http.build();
        }
    @Bean
    // UserDetailsService : Creating own users instead of default users(application properties) which is provided in the function.
    public UserDetailsService userDetailsService(){  
        UserDetails user1 = User.withUsername("user1").password("{noop}1111").roles("USER").build();
        UserDetails admin = User.withUsername("admin").password("{noop}2222").roles("ADMIN").build();
        // JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(datasource);

        return new InMemoryUserDetailsManager(user1,admin);
    }
}

package com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.config;

import com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/css/**", "/js/**", "/images/**", "/login", "/showRegister", "/register").permitAll()
                        .requestMatchers("/products/**").hasRole("ADMIN")
                        .requestMatchers("/cart/**", "/products/**").hasAnyRole("CUSTOMER", "ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .permitAll()
                        .successHandler((request, response, authentication) -> {
                            authentication.getAuthorities().forEach(grantedAuthority -> {
                                String role = grantedAuthority.getAuthority();

                                try {
                                    if (role.equals("ADMIN")) {
                                        response.sendRedirect("/products");
                                    } else if (role.equals("CUSTOMER")) {
                                        response.sendRedirect("/products");
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            });
                        })
                )
                .logout(logout -> logout.logoutSuccessUrl("/login").permitAll())
                .userDetailsService(customUserDetailsService);

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
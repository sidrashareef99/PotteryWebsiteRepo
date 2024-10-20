package com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.config;

import com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.service.CustomUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/css/**", "/js/**", "/images/**", "/login", "/showRegister", "/register","/newCart").permitAll()
                        .requestMatchers("/products/**").hasRole("ADMIN")
                        .requestMatchers("/cart/**", "/products/**").hasAnyRole("CUSTOMER", "ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .permitAll()
                        .successHandler((request, response, authentication) -> {
                            authentication.getAuthorities().forEach(grantedAuthority -> {
                                UserDetails u = customUserDetailsService.loadUserByUsername("sidrashareef99@gmail.com");
                                String role = grantedAuthority.getAuthority();
                               // String role  = u.getAuthorities().iterator().next().toString();

                                try {
                                    if (role.equals("ROLE_ADMIN")) {
                                        log.info("admin");
                                        response.sendRedirect("/adminIndex");
                                    } else if (role.equals("ROLE_CUSTOMER")) {
                                        log.info("customer");
                                        response.sendRedirect("/");
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
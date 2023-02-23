package com.kakao.tech.bookmanager.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig {

    @Bean
    fun userDetailsService(): UserDetailsService {
        return InMemoryUserDetailsManager(
            User.builder()
                .username("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build(),
            User.builder()
                .username("manager")
                .password(passwordEncoder().encode("password"))
                .roles("MANAGER")
                .build()
        )
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    @Throws(Exception::class)
    fun defaultSecurityFilterChain(http: HttpSecurity): SecurityFilterChain? {
        http
            .authorizeHttpRequests {
                it.requestMatchers(HttpMethod.POST, "/api/books").hasRole("MANAGER")
                    .requestMatchers(HttpMethod.PUT, "/api/books/**").hasRole("MANAGER")
                    .requestMatchers(HttpMethod.DELETE, "/api/books/**").hasRole("MANAGER")
                    .requestMatchers(HttpMethod.POST, "/api/categories").hasRole("MANAGER")
                    .requestMatchers(HttpMethod.PUT, "/api/categories/**").hasRole("MANAGER")
                    .requestMatchers(HttpMethod.DELETE, "/api/categories/**").hasRole("MANAGER")
                    .requestMatchers(HttpMethod.POST, "/api/categories/**").hasRole("MANAGER")
                    .anyRequest().hasRole("USER")
            }
            .formLogin()
            .and().httpBasic()
            .and().csrf().disable()
        return http.build()
    }
}




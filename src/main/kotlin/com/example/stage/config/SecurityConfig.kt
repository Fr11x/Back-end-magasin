package com.example.stage.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        // Configure les règles de sécurité
        http
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers("/api/auth/**", "/api/roles/**", "/api/utilisateur/**", "/inventaire/**").permitAll() // Autorise l'accès à /api/auth/**
                    .anyRequest().authenticated() // Exige une authentification pour toutes les autres requêtes
            }
            .csrf { csrf ->
                csrf.disable() // Désactive CSRF pour les APIs REST
            }

        return http.build()
    }
}

package com.yourcompany.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {

    // Configure CORS globalement pour l'API
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/api/**") // Permet toutes les routes API
            .allowedOrigins("http://localhost:4200") // Autorise uniquement le frontend Angular
            .allowedMethods("GET", "POST", "PUT", "DELETE") // Méthodes HTTP autorisées
            .allowedHeaders("*") // Permet toutes les en-têtes
            .allowCredentials(true) // Autorise les cookies ou informations d'authentification
    }
}

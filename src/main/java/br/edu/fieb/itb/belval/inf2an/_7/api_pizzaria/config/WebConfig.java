package br.edu.fieb.itb.belval.inf2an._7.api_pizzaria.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Mapeia todas as rotas que começam com /api/
                .allowedOrigins("http://localhost:3000") // Permite a origem correta
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH") // Métodos permitidos
                .allowCredentials(true); // Permite cookies e autenticação, se necessário
    }
}

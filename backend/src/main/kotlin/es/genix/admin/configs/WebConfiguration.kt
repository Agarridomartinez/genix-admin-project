package es.genix.admin.configs

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class WebConfiguration(
    @Value("\${spring.profiles.active}") private val activeProfile: String
) : WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        if (activeProfile == "development") {
            registry.addMapping("/**")
                .allowedOrigins("http://localhost:3333/")
                .allowedHeaders("*")
                .allowedMethods("GET", "POST", "HEAD", "OPTIONS", "PUT", "PATCH", "DELETE")
                .allowCredentials(true)
        } else {
            registry.addMapping("/**")
                .allowedOrigins("https://genix.ironmike.es")
                .allowedHeaders("*")
                .allowedMethods("GET", "POST", "HEAD", "OPTIONS", "PUT", "PATCH", "DELETE")
                .allowCredentials(true)
        }
    }
}

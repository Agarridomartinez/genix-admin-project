package es.genix.admin.configs

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties("app.discord")
@ConstructorBinding
data class DiscordProperties(  //clases inmutables de solo lectura que se crean una sola vez.
    val clientId: String,
    val clientSecret: String,
    val scope: Array<String>,
    val redirectUri: String,
    val clientName: String,
)

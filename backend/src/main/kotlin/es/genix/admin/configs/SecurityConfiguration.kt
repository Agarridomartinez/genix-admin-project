package es.genix.admin.configs

import es.genix.admin.utils.OAuth2UserAgentUtils.withUserAgent
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.RequestEntity
import org.springframework.http.ResponseCookie
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequestEntityConverter
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequestEntityConverter
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.core.user.OAuth2User
import javax.servlet.http.Cookie


@Configuration
class SecurityConfiguration : WebSecurityConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
            .httpBasic().disable()
            .cors().and().csrf().disable()
            .authorizeRequests()
            .antMatchers("/auth/redirect", "/auth/me", "/auth/login").permitAll()
            .anyRequest().authenticated()
            .and()
            .oauth2Login()
            .defaultSuccessUrl("/auth/redirect")
            .tokenEndpoint().accessTokenResponseClient(accessTokenResponseClient())
            .and()
            .userInfoEndpoint().userService(userService())
    }

    @Bean
    fun accessTokenResponseClient(): OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> {
        val client = DefaultAuthorizationCodeTokenResponseClient()

        client.setRequestEntityConverter(object : OAuth2AuthorizationCodeGrantRequestEntityConverter() {
            override fun convert(oauth2Request: OAuth2AuthorizationCodeGrantRequest): RequestEntity<*>? {
                return super.convert(oauth2Request)?.let { withUserAgent(it) }
            }
        })
        return client
    }

    @Bean
    fun userService(): OAuth2UserService<OAuth2UserRequest, OAuth2User> {
        val service = DefaultOAuth2UserService()
        service.setRequestEntityConverter(object : OAuth2UserRequestEntityConverter() {
            override fun convert(userRequest: OAuth2UserRequest): RequestEntity<*>? {
                return super.convert(userRequest)?.let { withUserAgent(it) }
            }
        })
        return service
    }
}

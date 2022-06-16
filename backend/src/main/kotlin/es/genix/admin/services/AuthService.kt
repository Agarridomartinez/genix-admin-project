package es.genix.admin.services

import es.genix.admin.models.User
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken
import org.springframework.stereotype.Service

@Service
class AuthService(private val clientService: OAuth2AuthorizedClientService) {

    fun getAccessToken(): String? {
        val authentication: Authentication = SecurityContextHolder
            .getContext()
            .authentication

        if (authentication is OAuth2AuthenticationToken) {
            val authorizedClient: OAuth2AuthorizedClient? = clientService.loadAuthorizedClient(
                authentication.authorizedClientRegistrationId,
                authentication.name
            )

            return authorizedClient?.accessToken?.tokenValue
        }

        return null
    }

    fun getLoggedUser(): User? {
        val authentication: Authentication = SecurityContextHolder
            .getContext()
            .authentication

        if (authentication is OAuth2AuthenticationToken) {
            return User(authentication.principal)
        }

        return null
    }

}

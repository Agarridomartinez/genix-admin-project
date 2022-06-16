package es.genix.admin.controllers

import es.genix.admin.services.AuthService
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseCookie
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletResponse


@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService,
    @Value("\${app.frontUrl}") private val frontUrl: String
) {

    @GetMapping("/login")
    fun loginFromFrontend(response: HttpServletResponse) = response.sendRedirect("/oauth2/authorization/discord")

    @GetMapping("/redirect")
    fun redirectToFront(response: HttpServletResponse) = response.sendRedirect(frontUrl)

    @GetMapping("/me")
    fun login(@AuthenticationPrincipal principal: OAuth2User?): ResponseEntity<Any> {

        if (principal == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized")

        val loggedUser = authService.getLoggedUser()

        return ResponseEntity.ok(loggedUser);
    }
}


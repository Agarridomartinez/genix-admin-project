package es.genix.admin.utils

import org.springframework.http.HttpHeaders
import org.springframework.http.RequestEntity

internal object OAuth2UserAgentUtils {
    fun withUserAgent(request: RequestEntity<*>): RequestEntity<*> {
        val headers = HttpHeaders()
        headers.putAll(request.headers)
        headers.add(HttpHeaders.USER_AGENT, DISCORD_BOT_USER_AGENT)
        return RequestEntity(request.body, headers, request.method, request.url)
    }

    private const val DISCORD_BOT_USER_AGENT = "Genix (https://genix.es/)"
}

package es.genix.admin.services

import es.genix.admin.configs.DiscordProperties
import io.mokulu.discord.oauth.DiscordAPI
import io.mokulu.discord.oauth.DiscordOAuth
import io.mokulu.discord.oauth.model.Guild
import io.mokulu.discord.oauth.model.TokensResponse
import io.mokulu.discord.oauth.model.User
import org.springframework.stereotype.Service

@Service
class DiscordService(
    private val discordProperties: DiscordProperties,
    private val authService: AuthService,
    private val guildService: GuildService
) {

    fun getOAuthUrl(): String = getDiscordOAuth().getAuthorizationURL(null)

    fun getUserGuilds(): List<Guild>? {
        try {
            val userGuilds = authService.getAccessToken()?.let { getDiscordAPI(it).fetchGuilds() }
            val botGuilds = guildService.getGuilds()

            val filteredGuilds = userGuilds?.filter { guild ->
                botGuilds.find { it.id == guild.id } != null
            }

            return filteredGuilds
        } catch (e: Exception) {
            throw e
        }
    }

    private fun getDiscordOAuth() = DiscordOAuth(
        discordProperties.clientId,
        discordProperties.clientSecret,
        discordProperties.redirectUri,
        discordProperties.scope
    )

    private fun getDiscordAPI(accessToken: String) = DiscordAPI(accessToken)
}


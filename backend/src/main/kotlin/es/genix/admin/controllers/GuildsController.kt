package es.genix.admin.controllers

import es.genix.admin.services.DiscordService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/guilds")
class GuildsController(private val discordService: DiscordService) {

    @GetMapping
    fun getUserGuilds() = discordService.getUserGuilds()
}

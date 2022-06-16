package es.genix.admin.services

import es.genix.admin.models.Guild
import es.genix.admin.models.User
import es.genix.admin.repositories.GuildRepository
import org.springframework.stereotype.Service

@Service
class GuildService(private val guildRepository: GuildRepository) {
    fun getGuilds(): MutableList<Guild> = guildRepository.findAll()
}

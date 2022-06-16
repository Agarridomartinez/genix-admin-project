package es.genix.admin.services

import es.genix.admin.domain.*
import es.genix.admin.events.PlayerEvent
import es.genix.admin.models.Guild
import es.genix.admin.repositories.GuildRepository
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class RabbitService(
    private val publisher: ApplicationEventPublisher,
    private val guildRepository: GuildRepository
) {

    @RabbitListener(queues = ["play.song"])
    fun receivePlaySongEvent(song: Song) {
        val id = song.user!!.id!!
        val guildId = song.member!!.guild!!.id!!

        publisher.publishEvent(PlayerEvent.PlaySong(id, guildId, song))
    }

    @RabbitListener(queues = ["stop.song"])
    fun receiveStopSongEvent(payload: StopPayload) {
        publisher.publishEvent(PlayerEvent.StopSong(payload.id, payload.guildId, payload.status))
    }

    @RabbitListener(queues = ["pause.song"])
    fun receivePauseSongEvent(payload: PausePayload) {
        publisher.publishEvent(PlayerEvent.PauseSong(payload.id, payload.guildId, payload.status))
    }

    @RabbitListener(queues = ["resume.song"])
    fun receiveResumeSongEvent(payload: ResumePayload) {
        publisher.publishEvent(PlayerEvent.ResumeSong(payload.id, payload.guildId, payload.status))
    }

    @RabbitListener(queues = ["skip.song"])
    fun receiveSkipSongEvent(payload: SkipPayload) {
        publisher.publishEvent(PlayerEvent.SkipSong(payload.id, payload.guildId, payload.status))
    }

    @RabbitListener(queues = ["add.song"])
    fun receiveAddSongEvent(song: Song) {
        val id = song.user!!.id!!
        val guildId = song.member!!.guild!!.id!!

        publisher.publishEvent(PlayerEvent.AddSong(id, guildId, song))
    }

    @RabbitListener(queues = ["add.list"])
    fun receiveAddListEvent(playlist: Playlist) {
        val id = playlist.user!!.id!!
        val guildId = playlist.member!!.guild!!.id!!

        publisher.publishEvent(PlayerEvent.AddList(id, guildId, playlist))
    }

    @RabbitListener(queues = ["bot.guilds"])
    fun receiveBotGuildsEvent(guilds: List<Guild>) {
        guildRepository.saveAll(guilds)
    }

    @RabbitListener(queues = ["bot.guilds.delete"])
    fun receiveBotGuildsDeleteEvent(guilds: List<Guild>) {
        guildRepository.deleteAll(guilds)
    }

    @RabbitListener(queues = ["queue"])
    fun receiveQueue(queue: Queue) {
        val id = queue.id!!
        val guildId = queue.guildId!!

        publisher.publishEvent(PlayerEvent.Queue(id, guildId, queue.songs!!))
    }
}


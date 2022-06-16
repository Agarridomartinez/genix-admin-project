package es.genix.admin.events

import es.genix.admin.domain.Playlist
import es.genix.admin.domain.Song
import org.springframework.context.ApplicationEvent

sealed class PlayerEvent(open val id: String, open val guildId: String, open val payload: Any) : ApplicationEvent(id) {
    data class AddSong(override val id: String, override val guildId: String, override val payload: Song) :
        PlayerEvent(id, guildId, payload)

    data class AddList(override val id: String, override val guildId: String, override val payload: Playlist) :
        PlayerEvent(id, guildId, payload)

    data class PlaySong(override val id: String, override val guildId: String, override val payload: Song) :
        PlayerEvent(id, guildId, payload)

    data class StopSong(override val id: String, override val guildId: String, override val payload: String) :
        PlayerEvent(id, guildId, payload)

    data class PauseSong(override val id: String, override val guildId: String, override val payload: String) :
        PlayerEvent(id, guildId, payload)

    data class ResumeSong(override val id: String, override val guildId: String, override val payload: String) :
        PlayerEvent(id, guildId, payload)

    data class SkipSong(override val id: String, override val guildId: String, override val payload: String) :
        PlayerEvent(id, guildId, payload)

    data class Queue(override val id: String, override val guildId: String, override val payload: List<Song>) :
        PlayerEvent(id, guildId, payload)

    data class AddVideoNotification(override val id: String, override val guildId: String, override val payload: Song) :
        PlayerEvent(id, guildId, payload)
    data class AddVideoNextNotification(override val id: String, override val guildId: String, override val payload: Song) :
        PlayerEvent(id, guildId, payload)
}

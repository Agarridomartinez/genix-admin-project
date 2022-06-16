package es.genix.admin.services

import es.genix.admin.domain.SocketEventType
import es.genix.admin.events.PlayerEvent
import es.genix.admin.events.SocketEvent
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.context.event.EventListener
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.stereotype.Service

@Service
class PlayerService(
    private val messageClient: SimpMessageSendingOperations,
    private val rabbitTemplate: RabbitTemplate,
    private val authService: AuthService
) {

    @EventListener
    fun handlePlaySongEvent(event: PlayerEvent.PlaySong) {
        messageClient.convertAndSend("/player/${event.guildId}", SocketEvent(SocketEventType.PLAY, event))
    }

    @EventListener
    fun handlePauseSongEvent(event: PlayerEvent.PauseSong) {
        messageClient.convertAndSend("/player/${event.guildId}", SocketEvent(SocketEventType.PAUSE, event))
    }

    @EventListener
    fun handleResumeSongEvent(event: PlayerEvent.ResumeSong) {
        messageClient.convertAndSend("/player/${event.guildId}", SocketEvent(SocketEventType.RESUME, event))
    }

    @EventListener
    fun handleStopSongEvent(event: PlayerEvent.StopSong) {
        messageClient.convertAndSend("/player/${event.guildId}", SocketEvent(SocketEventType.STOP, event))
    }

    @EventListener
    fun handleSkipSongEvent(event: PlayerEvent.SkipSong) {
        messageClient.convertAndSend("/player/${event.guildId}", SocketEvent(SocketEventType.SKIP, event))
    }

    @EventListener
    fun handleAddSongEvent(event: PlayerEvent.AddSong) {
        messageClient.convertAndSend("/player/${event.guildId}", SocketEvent(SocketEventType.ADD_SONG, event))
    }

    @EventListener
    fun handleAddListEvent(event: PlayerEvent.AddList) {
        messageClient.convertAndSend("/player/${event.guildId}", SocketEvent(SocketEventType.ADD_LIST, event))
    }

    @EventListener
    fun handleAddListEvent(event: PlayerEvent.Queue) {
        messageClient.convertAndSend("/player/${event.guildId}", SocketEvent(SocketEventType.QUEUE, event))
    }

    @EventListener
    fun handleAddVideoNotificationEvent(event: PlayerEvent.AddVideoNotification) {
        val id = authService.getLoggedUser()?.id
        messageClient.convertAndSendToUser(id!!,"/notification", SocketEvent(SocketEventType.ADD_VIDEO, event))
    }

    @EventListener
    fun handleAddVideoNextNotificationEvent(event: PlayerEvent.AddVideoNextNotification) {
        val id = authService.getLoggedUser()?.id
        messageClient.convertAndSendToUser(id!!,"/notification", SocketEvent(SocketEventType.ADD_VIDEO_NEXT, event))
    }

    fun getCurrentSong(guildId: String) {
        rabbitTemplate.convertAndSend("current.song", mapOf("guildId" to guildId))
    }

    fun pauseSong(id: String?, guildId: String?) {
        rabbitTemplate.convertAndSend("song.pause", mapOf("id" to id, "guildId" to guildId))
    }

    fun resumeSong(id: String?, guildId: String?) {
        rabbitTemplate.convertAndSend("song.resume", mapOf("id" to id, "guildId" to guildId))
    }

    fun skipSong(id: String?, guildId: String?) {
        rabbitTemplate.convertAndSend("song.skip", mapOf("id" to id, "guildId" to guildId))
    }

    fun stopSong(id: String?, guildId: String?) {
        rabbitTemplate.convertAndSend("song.stop", mapOf("id" to id, "guildId" to guildId))
    }

    fun getQueue(id: String, guildId: String) {
        rabbitTemplate.convertAndSend("current.queue", mapOf("id" to id, "guildId" to guildId))
    }

    fun playSong(id: String, guildId: String, videoId: String)  {
        rabbitTemplate.convertAndSend("player.play.song", mapOf("id" to id, "guildId" to guildId, "videoId" to videoId))
    }

    fun playSongNext(id: String, guildId: String, videoId: String) {
        rabbitTemplate.convertAndSend("player.play.next.song", mapOf("id" to id, "guildId" to guildId, "videoId" to videoId))
    }

    fun addSongToQueue(id: String, guildId: String, videoId: String) {
        rabbitTemplate.convertAndSend("player.add.queue.song", mapOf("id" to id, "guildId" to guildId, "videoId" to videoId))
    }
}

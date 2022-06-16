package es.genix.admin.controllers

import es.genix.admin.domain.PlayerInfo
import es.genix.admin.domain.PlayerVideoInfo
import es.genix.admin.services.PlayerService
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam


@Controller
class PlayerController(private val playerService: PlayerService) {

    @MessageMapping("/pause")
    fun pausePlayer(playerInfo: PlayerInfo) = playerService.pauseSong(playerInfo.id, playerInfo.guildId)

    @MessageMapping("/resume")
    fun resumePlayer(playerInfo: PlayerInfo) = playerService.resumeSong(playerInfo.id, playerInfo.guildId)

    @MessageMapping("/skip")
    fun skipPlayer(playerInfo: PlayerInfo) = playerService.skipSong(playerInfo.id, playerInfo.guildId)

    @MessageMapping("/stop")
    fun stopPlayer(playerInfo: PlayerInfo) = playerService.stopSong(playerInfo.id, playerInfo.guildId)

    @MessageMapping("/current")
    fun currentSong(playerInfo: PlayerInfo) = playerService.getCurrentSong(playerInfo.guildId)

    @MessageMapping("/queue")
    fun getQueue(playerInfo: PlayerInfo) = playerService.getQueue(playerInfo.id, playerInfo.guildId)

    @MessageMapping("/play")
    fun playSong(videoInfo: PlayerVideoInfo) = playerService.playSong(videoInfo.id, videoInfo.guildId, videoInfo.videoId)

    @MessageMapping("/play/next")
    fun playSongNext(videoInfo: PlayerVideoInfo) = playerService.playSongNext(videoInfo.id, videoInfo.guildId, videoInfo.videoId)

    @MessageMapping("/add")
    fun addSong(videoInfo: PlayerVideoInfo) = playerService.addSongToQueue(videoInfo.id, videoInfo.guildId, videoInfo.videoId)
}

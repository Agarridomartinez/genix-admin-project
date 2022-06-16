import { defineStore } from "pinia"
import type { PlayerStatus, Song } from "@/types"

export const usePlayerStore = defineStore("player", () => {
  const status = ref("stopped" as PlayerStatus)
  const currentSong = ref<Song | null>(null)

  function changeStatus(playerStatus: PlayerStatus) {
    status.value = playerStatus
  }

  function pause() {
    changeStatus("paused")
  }

  function resume() {
    changeStatus("playing")
  }

  function skip() {
    changeStatus("skipping")
  }

  function stop() {
    changeStatus("stopped")
    currentSong.value = null
  }
  return {
    status,
    changeStatus,
    currentSong,
    pause,
    resume,
    skip,
    stop,
  }
})

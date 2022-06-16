import { sendMessage } from "@/composables/socket/index"
import type { EventHandler } from "@/types"

export const eventHandlers: EventHandler = {
  play: ({ playerStore, guildStore }, event) => {
    if (event.guildId === guildStore.selectedGuild.id) {
      playerStore.currentSong = event.payload
      playerStore.status = "playing"
    }

    sendMessage("/genix-socket/queue", {
      id: event.id,
      guildId: event.guildId,
    })
  },
  stop: ({ playerStore, guildStore }, event) => {
    if (event.guildId === guildStore.selectedGuild.id)
      playerStore.stop()

    guildStore.emptyQueue(event.guildId)
  },
  pause: ({ playerStore, guildStore }, event) => {
    if (event.guildId === guildStore.selectedGuild.id)
      playerStore.pause()
  },
  resume: ({ playerStore, guildStore }, event) => {
    if (event.guildId === guildStore.selectedGuild.id)
      playerStore.resume()
  },
  skip: ({ playerStore, guildStore }, event) => {
    if (event.guildId === guildStore.selectedGuild.id)
      playerStore.skip()
  },
  queue: ({ guildStore }, event) => {
    guildStore.addQueueToGuild(event.guildId, event.payload)
  },
  add_song: ({ guildStore }, event) => {
    if (event.guildId === guildStore.selectedGuild.id) {
      sendMessage("/genix-socket/queue", {
        id: event.id,
        guildId: event.guildId,
      })
    }
  },
  add_list: ({ guildStore }, event) => {
    if (event.guildId === guildStore.selectedGuild.id) {
      sendMessage("/genix-socket/queue", {
        id: event.id,
        guildId: event.guildId,
      })
    }
  },
  add_video: (context, event) => {
    // TODO
  },
  add_video_next: (context, event) => {
    // TODO
  },
}

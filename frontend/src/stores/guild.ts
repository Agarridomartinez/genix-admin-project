import { defineStore } from "pinia"
import { initSubscriptions } from "@/composables/socket"
import type { Song } from "@/types"

export interface Guild {
  id: string
  name: string
  icon: string
  features: string[]
}

export interface Queue {
  id: string
  songs: Song[]
}

export const useGuildStore = defineStore("guild", () => {
  const guilds = ref<Array<Guild>>([])
  const selectedGuild = ref<Guild>({} as Guild)
  const queues = ref<Array<Queue>>([])

  function emptyQueue(id: string) {
    const queue = getQueue(id)

    if (queue)
      queues.value.splice(queues.value.indexOf(queue), 1)
  }

  function getQueue(id: string) {
    return queues.value.find(queue => queue.id === id)
  }

  function getCurrentQueue() {
    return queues.value.find(queue => queue.id === selectedGuild.value.id)
  }

  function getGuild(id: string): Guild | undefined {
    return guilds.value.find(guild => guild.id === id)
  }

  function setGuilds(newGuilds: Guild[]) {
    guilds.value.push(...newGuilds)

    selectedGuild.value = guilds.value[0]
    initSubscriptions(guilds.value)
  }

  function addQueueToGuild(guildId: string, queue: Array<Song>) {
    const guildQueue = queues.value.find(q => q.id === guildId)
    if (guildQueue)
      guildQueue.songs = [...queue]
    else
      queues.value.push({ id: guildId, songs: queue })
  }

  return {
    queues,
    guilds,
    selectedGuild,
    emptyQueue,
    getQueue,
    getCurrentQueue,
    getGuild,
    setGuilds,
    addQueueToGuild,
  }
})

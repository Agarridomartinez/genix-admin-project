import Stomp from "webstomp-client"

import type { Guild } from "@/stores/guild"
import { guildSubscriptions, subscriptions } from "@/composables/socket/subscriptions"

const domainName = import.meta.env.VITE_GENIX_API_DOMAIN as string

const socket = new WebSocket(`${import.meta.env.PROD ? "wss" : "ws"}://${domainName}/genix-socket`)
const stompClient = Stomp.over(socket)
const debugCallback = stompClient.debug

const isSocketConnected = ref(false)

const initSubscriptions = (guilds: Guild[]) => {
  const keys = Object.keys(subscriptions)
  const guildKeys = Object.keys(guildSubscriptions)

  for (const key of keys)
    stompClient.subscribe(key, subscriptions[key])

  for (const key of guildKeys) {
    for (const guild of guilds)
      stompClient.subscribe(key.replace("{guild}", guild.id), guildSubscriptions[key])
  }
}

const connectCallback = () => {
  isSocketConnected.value = true
}

const errorCallback = (error: any) => {
  isSocketConnected.value = false
  console.error(error)
}

const initSocket = (debug: boolean) => {
  const headers = {}

  if (!debug)
    stompClient.debug = () => {}
  else
    stompClient.debug = debugCallback

  stompClient.connect(
    headers,
    connectCallback,
    errorCallback,
  )
}

const sendMessage = (destination: string, message: Object) => {
  stompClient.send(destination, JSON.stringify(message))
}

export {
  isSocketConnected,
  initSocket,
  initSubscriptions,
  sendMessage,
}

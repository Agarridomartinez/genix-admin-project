import type { Message } from "webstomp-client"
import { usePlayerStore } from "@/stores/player"
import { useGuildStore } from "@/stores/guild"

import { eventHandlers } from "@/composables/socket/events"
import type { EventContext, GuildSubscription, SocketEventType, Subscription } from "@/types"

export const subscriptions: Subscription = {
  "/notification": (payload: Message) => {
    const playerStore = usePlayerStore()
    const guildStore = useGuildStore()
    const socketEvent = JSON.parse(payload.body)

    const events = Object.keys(eventHandlers)

    if (events.includes(socketEvent.type.toLowerCase()))
      eventHandlers[socketEvent.type.toLowerCase() as SocketEventType]({ playerStore, guildStore } as EventContext, socketEvent.event)
  },
}

export const guildSubscriptions: GuildSubscription = {
  "/player/{guild}": (payload: Message) => {
    const playerStore = usePlayerStore()
    const guildStore = useGuildStore()
    const socketEvent = JSON.parse(payload.body)

    const events = Object.keys(eventHandlers)

    if (events.includes(socketEvent.type.toLowerCase()))
      eventHandlers[socketEvent.type.toLowerCase() as SocketEventType]({ playerStore, guildStore } as EventContext, socketEvent.event)
  },
}

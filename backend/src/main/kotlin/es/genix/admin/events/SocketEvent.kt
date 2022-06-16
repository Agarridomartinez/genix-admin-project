package es.genix.admin.events

import es.genix.admin.domain.SocketEventType

data class SocketEvent(
    val type: SocketEventType,
    val event: PlayerEvent
)

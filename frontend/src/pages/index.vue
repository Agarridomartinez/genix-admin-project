<script setup lang="ts">

import { useGuildStore } from "@/stores/guild"

const guildStore = useGuildStore()
</script>

<template>
  <Container relative title="Dashboard" subtitle="¡Bienvenido a la adminitración de Genix!">
    <transition-group class="grid grid-cols-3 gap-4" name="queue" tag="div">
      <GuildCard v-for="queue in guildStore.queues" :key="queue.id" :queue="queue" />
    </transition-group>
  </Container>
</template>

<style scoped>
/* moving */
.queue-move {
  transition: all 600ms ease-in-out 50ms;
}

/* appearing */
.queue-enter-active {
  transition: all 400ms ease-out;
}

/* disappearing */
.queue-leave-active {
  transition: all 200ms ease-in;
  transform: scale(0.5);
  z-index: 0;
}

/* appear at / disappear to */
.queue-enter,
.queue-leave-to {
  opacity: 0;
}
</style>

<route lang="yml">
meta:
  requiresAuth: true
</route>

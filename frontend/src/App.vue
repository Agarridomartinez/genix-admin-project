<script setup lang="ts">
import Header from "@/components/Header.vue"
import Player from "@/components/Player.vue"
import { useAuthStore } from "@/stores/auth"

const authStore = useAuthStore()
const loggedIn = ref(false)

onMounted(async() => {
  loggedIn.value = await authStore.isLoggedIn()
})
</script>

<template>
  <Header v-if="loggedIn" text="center black dark:gray-200" />
  <Waves>
    <router-view v-slot="{ Component }">
      <transition name="scale" mode="out-in">
        <component :is="Component" />
      </transition>
    </router-view>
  </Waves>
  <Player v-if="loggedIn" text="gray-700 dark:gray-200" />
</template>

<style>
.scale-enter-active,
.scale-leave-active {
  transition: all 0.5s ease;
}

.scale-enter-from,
.scale-leave-to {
  opacity: 0;
  transform: scale(0.9);
}
</style>

<!-- Guía de colores
Background #060818
Component background #0e1726
navbar background #191e3a
nav-bar active item font color #191e3a
-->

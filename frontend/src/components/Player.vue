<script setup lang="ts">
import { usePlayerStore } from "@/stores/player"
import { sendMessage, toGuild } from "@/composables/socket"
import { useAuthStore } from "@/stores/auth"
import { useGuildStore } from "@/stores/guild"

const playerStore = usePlayerStore()
const authStore = useAuthStore()
const guildStore = useGuildStore()

const playerStyles = computed(() => {
  return {
    transform: playerStore.currentSong != null ? "translate(-50%, 0)" : "translate(-50%, 224px)",
  }
})

function handleOnPlay(isPlaying: boolean) {
  sendMessage(isPlaying ? "/genix-socket/pause" : "/genix-socket/resume", {
    id: authStore.user?.id,
    guildId: guildStore.selectedGuild.id,
  })
}
function handleOnStop() {
  sendMessage("/genix-socket/stop", {
    id: authStore.user?.id,
    guildId: guildStore.selectedGuild.id,
  })
}
function handleOnSkip() {
  sendMessage("/genix-socket/skip", {
    id: authStore.user?.id,
    guildId: guildStore.selectedGuild.id,
  })
}

</script>
<template>
  <div class="transition flex-row flex w-full px-4 h-max fixed bottom-[24px] left-[50%]" :style="playerStyles">
    <div
      transform-gpu
      backdrop-blur-lg
      backdrop-saturate="[180%]"
      rounded-xl m="l-auto r-auto" class="flex flex-row p-4 min-w-[600px] w-min h-[200px] xs:w-[100%]"
      bg="white/90 dark:gray-700/60" shadow="xl "
    >
      <div flex flex-row>
        <div h="[100%]" rounded overflow-hidden shadow="md dark:white/20" class="visible xs:invisible w-[40%] xs:w-[0%]">
          <img h="full" object-cover aspect-auto rounded :src="playerStore.currentSong?.thumbnail" alt="Song's thumbnail">
        </div>
        <div class="" flex flex-col items-stretch justify-evenly w="[57%]" m="l-[3%]">
          <h2 text-xl truncate>
            {{ playerStore.currentSong?.name }}
          </h2>
          <span text-xs mt-1>{{ playerStore.currentSong?.uploader?.name }} | Añadido por <strong
            px-2 rounded-xl bg="gray-500 dark:gray-200"
            color="white dark:black"
          >{{
            playerStore.currentSong?.user?.username
          }} #{{ playerStore.currentSong?.user?.discriminator }}</strong></span>
          <div mt-auto class="flex flex-row items-center justify-center">
            <StopButton :callback="handleOnStop" />
            <PlayButton :playing="playerStore.status === 'playing'" :callback="handleOnPlay" />
            <SkipButton :callback="handleOnSkip" />
          </div>
          <ProgressBar :duration="playerStore.currentSong?.duration" :status="playerStore.status" />
        </div>
      </div>
    </div>
  </div>
</template>

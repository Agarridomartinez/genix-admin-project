<script setup lang="ts">

import type { VideoResult } from "@/types"
import { sendMessage } from "@/composables/socket"
import { useAuthStore } from "@/stores/auth"
import { useGuildStore } from "@/stores/guild"

interface Props {
  video: VideoResult
}

const props = defineProps<Props>()
const authStore = useAuthStore()
const guildStore = useGuildStore()

// Websocket ->  RabbitMQ
// PLAY -> "player.play.song"
// PLAY_NEXT -> "player.play.next.song"
// ADD_TO_QUEUE -> "player.add.queue.song"

function handlePlayClick() {
  sendMessage("/genix-socket/play", { id: authStore.user?.id, guildId: guildStore.selectedGuild.id, videoId: props.video.id.videoId })
}

function handlePlayNextClick() {
  sendMessage("/genix-socket/play/next", { id: authStore.user?.id, guildId: guildStore.selectedGuild.id, videoId: props.video.id.videoId })
}

function handleAddQueueClick() {
  sendMessage("/genix-socket/play/next", { id: authStore.user?.id, guildId: guildStore.selectedGuild.id, videoId: props.video.id.videoId })
}
</script>

<template>
  <div flex flex-row items-center bg="gray-300" text-black dark="text-white bg-gray-700" rounded-xl p-4>
    <img w="[128px]" h="[128px]" aspect-ratio :src="props.video.snippet.thumbnails.medium.url" alt="">
    <div flex flex-col justify-start w-full h-full overflow-hidden>
      <h1 truncate ml-4 mr-4>
        {{ props.video.snippet.title }}
      </h1>

      <div w="[100%]" flex flex-row justify-around items-center mt-auto mb="[2rem]" h="[24px]">
        <button class="flex m-1 p-4 bg-gray-400/20 rounded-full" type="button" @click="handlePlayClick">
          <i class="w-[24px] h-[24px]" i-carbon-play />
        </button>
        <button class="flex m-1 p-4 bg-gray-400/20 rounded-full" type="button" @click="handlePlayNextClick">
          <i class="w-[24px] h-[24px]" i-carbon-page-last />
        </button>
        <button class="flex m-1 p-4 bg-gray-400/20 rounded-full" type="button" @click="handleAddQueueClick">
          <i class="w-[24px] h-[24px]" i-carbon-list />
        </button>
      </div>
    </div>
  </div>
</template>

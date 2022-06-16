<script setup lang="ts">
import type { Queue } from "@/stores/guild"
import { useGuildStore } from "@/stores/guild"
import { usePlayerStore } from "@/stores/player"

interface Props {
  queue: Queue
}

const playerStore = usePlayerStore()
const guildStore = useGuildStore()
const props = defineProps<Props>()

const guild = computed(() => guildStore.getGuild(props.queue.id))
const guildAvatar = computed(() => {
  if (!guild.value)
    return null
  return `background-image: linear-gradient(#fff0 0%, #fff0 70%, #1d1d1d 100%), url("https://cdn.discordapp.com/icons/${guild.value?.id}/${guild.value?.icon}");`
})

const totalDuration = computed(() => {
  const totalSeconds = props.queue.songs.reduce((acc, song) => acc + song.duration, 0)
  const seconds = totalSeconds % 60
  const minutes = Math.floor(totalSeconds / 60)
  const hours = Math.floor(minutes / 60)

  if (hours > 0)
    return `${hours}h ${minutes % 60}m ${seconds}s`
  if (minutes > 0)
    return `${minutes}m ${seconds}s`
  return `${seconds}s`
})
const playingSong = computed(() => {
  if (props.queue.songs.length === 0)
    return null
  return props.queue.songs[0]
})
const totalSongs = computed(() => props.queue.songs.length)
const tiltOptions = computed(() => ({
  "max": 10,
  "speed": 400,
  "scale": 1.05,
  "perspective-origin": "center center",
  "transition-duration": "0.5s",
  "transition-timing-function": "ease-in-out",
  "transition-property": "transform, box-shadow",
}))

</script>

<template>
  <Tilt :options="tiltOptions" :parallax="true" relative>
    <div max-w="[90%]" absolute top="[170px]" left="50%" style="transform: translateZ(20px) translateX(-50%)" flex flex-row items-center bg-gray-700 p-2 rounded-xl truncate>
      <i color-white i-carbon-play mr-3 />
      <div truncate>
        <h2 text-white>
          {{ playingSong.name }} - <small>{{ playingSong.uploader.name }}</small>
        </h2>
      </div>
    </div>
    <div bg="gray-600/50" p-3 rounded-xl absolute top="[40px]" left="[50%]" style="transform: translateZ(20px) translateX(-50%)">
      <span text-xs uppercase mb="[-10px]" text="white/60">GUILD</span>
      <h1 text-white text-2xl mb-auto ml-auto mr-auto px-6 font-roboto style="transform: translateZ(20px)">
        {{ guild.name }}
      </h1>
    </div>
    <div class="queue" flex flex-col items-start color="dark:gray-200" shadow="lg" rounded-lg overflow-hidden w-full min-h="[400px]" bg="white dark:gray-700">
      <div flex flex-row h="[40%]" overflow-hidden w-full relative>
        <div v-if="guildAvatar !== null" bg-center bg-cover w-full h-full bg-no-repeat :style="guildAvatar" alt="" />
        <div v-else bg-gray-200 />

        <div backdrop-blur backdrop-saturate-150 absolute bottom-0 right-0 bg="gray-700/50" text-white mt-auto grid grid-rows-3>
          <div flex flex-col p-2 border-b-2 border="white/20">
            <h1 text-sm>
              2
            </h1>
            <span text-xs>oyentes</span>
          </div>
          <div flex flex-col p-2 border-b-2 border="white/20">
            <h1 text-sm>
              {{ totalSongs }}
            </h1>
            <span text-xs>en cola</span>
          </div>
          <div flex flex-col p-2>
            <h1 text-sm>
              {{ totalDuration }}
            </h1>
            <span text-xs>duración</span>
          </div>
        </div>
      </div>
      <div px-4 py-2 flex flex-col items-start color="white" bg="gray-600 dark:gray-900" w-full h="[60%]" max-h="[60%]" data-simplebar style="overflow: auto">
        <transition-group class=" relative flex flex-col w-full items-center" name="song" tag="div">
          <h2 v-for="song in props.queue.songs" :key="song.id" class="song" flex flex-row items-center justify-start mb-1 p-2 truncate w-full>
            {{ song.name }}
          </h2>
        </transition-group>
      </div>
    </div>
  </Tilt>
</template>

<style scoped>
.queue {
  backface-visibility: hidden;
  z-index: 1;

  height: 300px;
}

.song {
  backface-visibility: hidden;
  z-index: 1;
  background: rgba(229,231,235,0.1);
  border-radius: 3px;
}

.song:first-child {
  visibility: hidden;
  margin-bottom: 12px;
  opacity: 0;
}

/* moving */
.song-move {
  transition: all 600ms ease-in-out 50ms;
}

/* appearing */
.song-enter-active {
  transition: all 400ms ease-out;
}

/* disappearing */
.song-leave-active {
  transition: all 200ms ease-in;
  position: absolute;
  top: 0;
  left: 0;
  transform: scale(0.5);
  z-index: 0;
}

/* appear at / disappear to */
.song-enter,
.song-leave-to {
  opacity: 0;
}
</style>

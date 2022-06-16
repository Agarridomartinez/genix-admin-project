<script  lang="ts" setup>

import type { PlayerStatus } from "@/types"
import { usePlayerStore } from "@/stores/player"

interface Props {
  duration: number
  status: PlayerStatus
}

const props = withDefaults(defineProps<Props>(), {
  duration: 0,
  status: "stopped",
})

const { duration, status } = toRefs(props)

const playerStore = usePlayerStore()
const { currentSong } = toRefs(playerStore)
const currentSeconds = ref(currentSong.value?.currentTime ?? 0)

const start = computed(() => formatSeconds(currentSeconds.value))
const end = computed(() => formatSeconds(duration.value))

const maskStyles = computed(() => {
  const width = currentSeconds.value / duration.value * 100
  return {
    width: `${width}%`,
  }
})

onMounted(() => {
  setInterval(tick, 1000)
})

watch(currentSong, (newValue) => {
  currentSeconds.value = newValue?.currentTime ?? 0
})

watch(status, (status: PlayerStatus) => {
  if (status === "stopped" || status === "skipping")
    currentSeconds.value = 0
})

function tick() {
  if (playerStore.status === "playing") {
    const current = currentSeconds.value + 1
    if (current < duration.value)
      currentSeconds.value = current
  }
}

function formatSeconds(value: number) {
  // Format seconds to minutes:seconds
  const minutes = Math.floor(value / 60)
  const seconds = Math.floor(value % 60)
  return `${minutes}:${seconds < 10 ? "0" : ""}${seconds}`
}

</script>

<template>
  <div flex flex-row items-center w-full>
    <div flex w="[60px]" m="r-1">
      <span m-auto>{{ start }}</span>
    </div>
    <div flex flex-row w-full h="[5px]" rounded bg="gray-400">
      <div transition class="mask" rounded :style="maskStyles" bg="[#0e1726]" />
    </div>
    <div flex w="[60px]" m="l-1">
      <span m-auto>{{ end }}</span>
    </div>
  </div>
</template>

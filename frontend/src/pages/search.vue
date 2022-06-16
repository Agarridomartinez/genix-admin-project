<script setup lang="ts">
import type { VideoResult } from "@/types"
import { useYoutubeSearch } from "@/composables"
import { useGuildStore } from "@/stores/guild"

const { getResults } = useYoutubeSearch()

const guildStore = useGuildStore()

const query = ref("")
const videoSearch = ref("")
const videoResults = ref<VideoResult[]>([])

let searchTimer: any = null

const searchTimerCallback = async() => {
  videoResults.value = await getResults(query.value)
  searchTimer = null
}

watch(videoSearch, async(newValue) => {
  query.value = newValue
  if (searchTimer) {
    clearTimeout(searchTimer)
    searchTimer = null
  }
  else {
    searchTimer = setTimeout(searchTimerCallback, 1000)
  }
})

</script>

<template>
  <Container title="Búsqueda" subtitle="Encuentra tus temas temardos favoritos y reprodúcelos en tu servidor de Discord">
    <div class="flex flex-row w-full">
      <div relative flex flex-row justify-start w="[65%]" h="[72px]">
        <i absolute color-gray-500 i-carbon-search w="[36px]" h="[36px]" top="[50%]" left="[15px]" transform="translate-y-[-50%]" />
        <input v-model="videoSearch" w="[34rem]" pl-15 color-gray-700 text-xl w-full p-4 rounded-xl outline-none shadow="lg dark:white/20" type="text" placeholder="Búsca tu canción / playlist favorita...">
      </div>
    </div>

    <div class="result-wrapper">
      <VideoResult v-for="video in videoResults" :key="video.id.videoId" :video="video" />
    </div>
  </Container>
</template>

<style scoped>

.result-wrapper {
  margin-top: 2rem;
  display: grid;
  grid-template-columns: repeat(3, minmax(15rem, 35rem));
  gap: 1rem;
}

</style>

<route lang="yml">
meta:
  requiresAuth: true
</route>

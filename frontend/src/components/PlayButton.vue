<script setup lang="ts">

interface Props {
  playing: boolean
  callback: (isPlaying: boolean) => void
}

const props = defineProps<Props>()

function handleClick() {
  props.callback(props.playing)
}

</script>

<template>
  <button class="flex m-1 p-4 bg-gray-400/20 rounded-full" type="button" @click="handleClick">
    <i v-if="props.playing" class="w-[24px] h-[24px]" i-carbon-pause />
    <i v-if="!props.playing" class="w-[24px] h-[24px]" i-carbon-play />
  </button>
</template>

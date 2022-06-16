<script setup lang=ts>
import type { TiltOptions } from "vanilla-tilt"
import VanillaTilt from "vanilla-tilt"

interface Props {
  options: TiltOptions
  parallax: boolean
}
const props = defineProps<Props>()
const tiltRef = ref(null)

onMounted(() => {
  VanillaTilt.init(tiltRef.value!, props.options)
})
</script>

<template>
  <div id="tiltMe" ref="tiltRef" :class="props.parallax ? 'preserve':''">
    <slot />
  </div>
</template>

<style>
.preserve {
    transform-style: preserve-3d;
}
</style>

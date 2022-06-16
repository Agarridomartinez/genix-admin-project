<script setup lang="ts">
interface Props {
  callback: () => void
}

const props = defineProps<Props>()

function handleClick() {
  props.callback()
}
</script>

<template>
  <button class="flex mr-auto p-4" type="button" @click="handleClick">
    <i class="w-[24px] h-[24px]" i-carbon-skip-forward />
  </button>
</template>

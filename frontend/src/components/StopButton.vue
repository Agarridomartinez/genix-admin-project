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
  <button class="flex ml-auto p-4" type="button" @click="handleClick">
    <i class="w-[24px] h-[24px]" i-carbon-stop />
  </button>
</template>

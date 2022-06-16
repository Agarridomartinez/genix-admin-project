<script setup lang="ts">

interface Props {
  title?: string
}

const props = withDefaults(defineProps<Props>(), {
  title: "Sección vacía",
})

</script>

<template>
  <div flex flex-col items-start w-full p-4 rounded-xl mb-3 bg="gray-400/20">
    <h2>{{ props.title }}</h2>
    <slot />
  </div>
</template>

<style scoped>
h2 {
  font-size: 18px;
  font-weight: lighter;
}
</style>

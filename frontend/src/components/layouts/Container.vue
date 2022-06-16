<script setup lang="ts">

const slots = useSlots()

interface Props {
  title?: string
  subtitle?: string
}

const props = withDefaults(defineProps<Props>(), {
  title: "Contenedor vacío",
  subtitle: undefined,
})
</script>

<template>
  <div flex flex-col items-start p-4 rounded-xl w-full h-full>
    <div flex flex-col items-start class="mb-[24px]">
      <h1>{{ props.title }}</h1>
      <h3 v-if="props.subtitle">
        {{ props.subtitle }}
      </h3>
    </div>
    <div class="container-wrapper flex flex-col w-full md:flex-row md:justify-center md:items-start">
      <section id="content" w-full>
        <slot />
      </section>
    </div>
  </div>
</template>

<style scoped>
h1 {
  font-size: 28px;
  font-weight: bold;
  margin-top: 12px;
}
h3 {
  font-size: 14px;
  font-weight: bold;
}
</style>

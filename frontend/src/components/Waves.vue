<script setup lang="ts">
</script>

<template>
  <div bg="gray-100 dark:gray-800" h-full>
    <img src="/bg.svg" alt="">
    <main font-sans h-full p="x-10 y-[104px]" text="center gray-700 dark:gray-200" z-10>
      <slot />
    </main>
  </div>
</template>

<style scoped>
  img {
    width: 100%;
    height: 100%;
    position: fixed;
    z-index: 0;
    top: 0;
    right: -30%;
    user-select: none;
    pointer-events: none;
  }

</style>

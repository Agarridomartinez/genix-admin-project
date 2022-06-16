<script setup lang="ts">
const baseUrl = import.meta.env.VITE_GENIX_API_URL
</script>

<template>
  <a :href="`${baseUrl}/auth/login`" flex m-auto mt-6 px-4 py-3 w-max text-white class="bg-[#7286da] " rounded-md>
    <img src="/discord-logo.svg" class="w-[24px] h-[24px] mr-3" alt="">
    Login con Discord
  </a>
</template>

<script setup lang="ts">
import DiscordLoginButton from "@/components/DiscordLoginButton.vue"

</script>

<template>
  <div flex flex-col items-center justify-center h-full>
    <div flex flex-col items-center rounded-xl shadow-xl justify-center w="[450px]" h="[450px]" bg="white">
      <h1 text-xl mb-5 class="dark:text-black">
        <strong tracking-widest>GENIX</strong> | Acceder
      </h1>
      <div mb="[50px]">
        <img shadow-lg rounded-full src="https://cdn.discordapp.com/app-icons/822959351030874172/7e66f079b99db7ce8c5d5a8b434c7409.webp?size=128" alt="Genix logo">
      </div>
      <div>
        <DiscordLoginButton shadow="lg [#7286da]/40" />
      </div>
    </div>
  </div>
</template>

<route lang="yml">
name: login
</route>

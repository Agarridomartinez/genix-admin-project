<script setup lang="ts">
import { useGuildsApi } from "@/composables"
import type { Guild } from "@/stores/guild"
import { useGuildStore } from "@/stores/guild"

</script>

<template>
  <nav z-10 fixed flex flex-row items-start w-full py-4 px-8>
    <section mr-auto>
      <ul flex flex-row px-6 py-4>
        <li mr-4>
          <RouterLink to="/">
            Home
          </RouterLink>
        </li>
        <li mr-4>
          <RouterLink to="/search">
            Búsqueda
          </RouterLink>
        </li>
      </ul>
    </section>
    <section flex flex-row ml-auto>
      <Profile />
    </section>
  </nav>
</template>

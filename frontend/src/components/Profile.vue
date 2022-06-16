<script setup lang="ts">
import { Listbox, ListboxButton, ListboxOption, ListboxOptions } from "@headlessui/vue"
import { CheckIcon, SelectorIcon } from "@heroicons/vue/outline"
import { useAuthStore } from "@/stores/auth"
import type { Guild } from "@/stores/guild"
import { useGuildStore } from "@/stores/guild"
import { useGuildsApi } from "@/composables"
import { sendMessage } from "@/composables/socket"
import { usePlayerStore } from "@/stores/player"

const guildsApi = useGuildsApi()

const authStore = useAuthStore()
const guildStore = useGuildStore()
const playerStore = usePlayerStore()

const bannerOpen = ref(false)
const guildRef = ref<Guild | null>(null)

watch(guildRef, (guild) => {
  if (guild != null) {
    guildStore.selectedGuild = guild
    playerStore.stop()
    bannerOpen.value = false
    sendMessage("/genix-socket/current", { id: authStore.user?.id, guildId: guildStore.selectedGuild.id })
  }
})

onMounted(async() => {
  try {
    const userGuilds = await guildsApi.getUserGuilds()

    guildStore.setGuilds(userGuilds.data)
    guildRef.value = guildStore.selectedGuild

    for (const guild of userGuilds.data) {
      sendMessage("/genix-socket/queue", {
        id: authStore.user?.id,
        guildId: guild.id,
      })
    }
  }
  catch (error: any) {
    console.warn(error)
  }
})

function onBannerClick() {
  bannerOpen.value = !bannerOpen.value
}

const bgStyles = computed(() => {
  const commonStyles = {
    backgroundRepeat: "no-repeat",
    backgroundSize: "cover",
    backgroundPosition: "center",
    height: "100px",
    bottom: "-60px",
  }

  if (guildStore.selectedGuild != null) {
    return {
      transform: bannerOpen.value ? "translateY(30px)" : "translateY(-20px)",
      backgroundImage: `url(https://cdn.discordapp.com/icons/${guildStore.selectedGuild.id}/${guildStore.selectedGuild.icon})`,
      ...commonStyles,
    }
  }
  else {
    return {
      transform: "translateY(0)",
      backgroundImage: "none",
      ...commonStyles,
    }
  }
})
</script>

<template>
  <div flex flex-col w-max relative>
    <div
      flex flex-row items-center justify-center bg-gray-200 w-full rounded-xl px-4 py-3 m-auto
      text-black shadow="xl dark:white/20" z-10
    >
      <img
        rounded-full mr-4 class="w-[48px] h-[48px]"
        :src="`https://cdn.discordapp.com/avatars/${authStore.user.id}/${authStore.user.avatar}.png`" alt=""
      >

      <div flex flex-col items-start>
        <h1>
          {{ authStore.user.username }} <small text-xs class="color-black/60">#{{
            authStore.user.discriminator
          }}</small>
        </h1>
        <h3 />
      </div>
    </div>
    <div flex flex-col w-full z-0 rounded-b-xl bg-gray-700 transition absolute transform-gpu :style="bgStyles">
      <div rounded-b-xl class="guild-banner" />
      <div v-if="guildRef != null" class="w-full m-auto px-2 pt-2 z-[1]">
        <Listbox v-model="guildRef">
          <div class="relative mt-1">
            <ListboxButton
              class="relative w-full py-2 pl-3 pr-10 text-left bg-white/75 saturate-[180%] backdrop-blur-md rounded-lg shadow-md cursor-default focus:outline-none focus-visible:ring-2 focus-visible:ring-opacity-75 focus-visible:ring-white focus-visible:ring-offset-orange-300 focus-visible:ring-offset-2 focus-visible:border-indigo-500 sm:text-sm"
            >
              <span class="block truncate text-black">{{ guildRef.name }}</span>
              <span
                class="absolute inset-y-0 right-0 flex items-center pr-2 pointer-events-none"
              >
                <SelectorIcon class="w-5 h-5 text-gray-400" aria-hidden="true" />
              </span>
            </ListboxButton>

            <transition
              leave-active-class="transition duration-100 ease-in"
              leave-from-class="opacity-100"
              leave-to-class="opacity-0"
            >
              <ListboxOptions
                data-simplebar
                data-simplebar-auto-hide="true"
                class="absolute w-full py-1 mt-1 overflow-auto text-base bg-white/75 saturate-[180%] backdrop-blur-md rounded-md shadow-lg max-h-60 ring-1 ring-black ring-opacity-5 focus:outline-none sm:text-sm"
              >
                <ListboxOption
                  v-for="guild in guildStore.guilds"
                  v-slot="{ active, selected }"
                  :key="guild.name"
                  :value="guild"
                  as="template"
                >
                  <li :class="[active ? 'text-amber-900 bg-amber-100' : 'text-gray-900','cursor-default select-none relative py-2 pl-10 pr-10',]">
                    <span :class="[selected ? 'font-medium' : 'font-normal', 'block truncate',]">
                      {{ guild.name }}
                    </span>
                    <span v-if="selected" class="absolute inset-y-0 left-0 flex items-center pl-3 text-amber-600">
                      <CheckIcon class="w-5 h-5" aria-hidden="true" />
                    </span>
                  </li>
                </ListboxOption>
              </ListboxOptions>
            </transition>
          </div>
        </Listbox>
      </div>
      <button
        type="button" :style="{ zIndex: bannerOpen ? '0': '10'}" text-center uppercase text-xs color-white
        rounded-b-xl flex ml-auto mr-auto mt-auto p-3 @click="onBannerClick"
      >
        {{ guildStore.selectedGuild.name }}
      </button>
    </div>
  </div>
</template>

<style scoped>
.guild-banner {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  backdrop-filter: blur(3px) saturate(180%);
  background: linear-gradient(to top, rgba(55, 65, 81, 0.8), rgba(55, 65, 81, 0.2));
}
</style>

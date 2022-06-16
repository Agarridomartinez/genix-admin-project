import { defineStore } from "pinia"
import type { DistubeUser } from "@/types"
import { useAuthApi } from "@/composables"

const GENIX_SESSION_LOGGED_IN = import.meta.env.VITE_GENIX_COOKIE_NAME as string
const authApi = useAuthApi()

function hasCookie(name: string) {
  return document.cookie.split(";").some(c => c.trim().startsWith(name))
}

export const useAuthStore = defineStore("auth", () => {
  const user = ref<DistubeUser | null>(null)

  async function isLoggedIn() {
    try {
      if (hasCookie(GENIX_SESSION_LOGGED_IN)) {
        const { data } = await authApi.me()
        user.value = data
      }
    }
    catch (error) {
      user.value = null
    }

    return user.value !== null
  }

  function logout() {
    user.value = null
  }

  return {
    user,
    isLoggedIn,
    logout,
  }
})

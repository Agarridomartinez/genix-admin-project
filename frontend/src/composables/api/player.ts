import { axiosInstance } from "@/composables/api/__axios"

export const usePlayerApi = () => ({
  getCurrentSong(guildId: string | undefined) {
    return axiosInstance.get(`/api/v1/player/current?guildId=${guildId}`)
  },
})

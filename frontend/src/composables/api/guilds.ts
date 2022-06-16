import { axiosInstance } from "@/composables/api/__axios"

export const useGuildsApi = () => ({
  getUserGuilds() {
    return axiosInstance.get("/api/v1/guilds")
  },
})

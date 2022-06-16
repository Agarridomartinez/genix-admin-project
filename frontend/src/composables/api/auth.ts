import { axiosInstance } from "@/composables/api/__axios"

export const useAuthApi = () => ({
  me() {
    return axiosInstance.get("/auth/me").catch((err) => { throw err })
  },
})

import axios from "axios"

export const axiosInstance = axios.create({
  withCredentials: true,
  baseURL: import.meta.env.VITE_GENIX_API_URL as string,
})

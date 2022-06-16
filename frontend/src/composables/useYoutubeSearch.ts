import type { VideoResult } from "@/types"

const API_URL = `https://www.googleapis.com/youtube/v3/search?key=${import.meta.env.VITE_GOOGLE_API_KEY}`

async function getResults(query: string, maxResults = 6) {
  try {
    const response = await fetch(`${API_URL}&type=video&part=snippet&q=${query}&maxResults=${maxResults}`)
    const data = await response.json()
    return data.items as VideoResult[]
  }
  catch (e) {
    return []
  }
}

export const useYoutubeSearch = () => {
  return {
    getResults,
  }
}

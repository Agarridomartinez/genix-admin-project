import { createPinia } from "pinia"
import { createApp } from "vue"
import { createRouter, createWebHistory } from "vue-router"
import routes from "virtual:generated-pages"
import App from "./App.vue"
import { initSocket } from "@/composables/socket"
import "simplebar/dist/simplebar.min.css"
import "simplebar/dist/simplebar.min.js"

import "@unocss/reset/tailwind.css"
import "./styles/main.css"
import "uno.css"
import { useAuthStore } from "@/stores/auth"

const app = createApp(App)
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
})

router.beforeEach(async(to) => {
  const authStore = useAuthStore()
  const isLoggedIn = await authStore.isLoggedIn()

  if (to.meta.requiresAuth && !isLoggedIn) {
    return {
      name: "login",
      // save the location we were at to come back later
      query: { redirect: to.fullPath },
    }
  }
})

initSocket(!import.meta.env.PROD)

app.use(router)
app.use(createPinia())
app.mount("#app")

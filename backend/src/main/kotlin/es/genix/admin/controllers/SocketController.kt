package es.genix.admin.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/genix-socket")
class SocketController {

    @GetMapping("/info")
    fun index(): String {
        return "redirect:/auth/redirect"
    }
}

package com.example.websocket.controller

import com.example.websocket.websocket.WebSocket
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpSession
import jakarta.websocket.Session
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView

@RestController
class MainController {
    private final var webSocket = WebSocket()
    @GetMapping(value = ["/index"])
    fun home(modelAndView: ModelAndView):ModelAndView{
        modelAndView.addObject("", "")
        modelAndView.viewName = "index"
        return modelAndView
    }

    @PostMapping(value = ["/websocket"])
    fun postMessage(@RequestBody message: String){
        webSocket.sendMessage(message)
        /*session.setAttribute("message", message)*/
    }
}
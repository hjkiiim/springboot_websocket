package com.example.websocket.controller

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpSession
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView

@RestController
class MainController {
    @GetMapping(value = ["/index"])
    fun home(modelAndView: ModelAndView):ModelAndView{
        modelAndView.addObject("", "")
        modelAndView.viewName = "index"
        return modelAndView
    }

    /*@PostMapping(value = ["/ws"])
    fun postMessage(@RequestBody message: String, request:HttpServletRequest){
        val session: HttpSession = request.session
        session.setAttribute("message", message)
    }*/
}
package com.example.websocket.config

import com.example.websocket.handler.WebSocketHandler
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.config.annotation.*
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor

@Configuration  // 설정파일 의미
@EnableWebSocket
class WebSocketConfig : WebSocketConfigurer{
    @Override
    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry){
            registry.addHandler(WebSocketHandler(), "/ws").setAllowedOriginPatterns("*").addInterceptors(HttpSessionHandshakeInterceptor())
    }

}
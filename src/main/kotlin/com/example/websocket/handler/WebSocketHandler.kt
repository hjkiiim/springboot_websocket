package com.example.websocket.handler

import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.util.*


class WebSocketHandler: TextWebSocketHandler() {

    var webSocketSessions = Collections.synchronizedList(ArrayList<WebSocketSession>())

    @Override
    @Throws(Exception::class)
    override fun afterConnectionEstablished(session: WebSocketSession){
        super.afterConnectionEstablished(session)
        webSocketSessions.add(session)
        println("연결 : " +  session.id);
    }

    @Override
    @Throws(Exception::class)
    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus){
        super.afterConnectionClosed(session, status)
        webSocketSessions.remove(session)
        println("연결 종료 : " + session.id)
    }

    @Override
    @Throws(Exception::class)
    override fun handleTextMessage(session: WebSocketSession, message: TextMessage){
        super.handleTextMessage(session, message)
        for(websocketSession: WebSocketSession in webSocketSessions){
            websocketSession.sendMessage(message)
        }
        println("Message : ${message.payload}")
        session.sendMessage(TextMessage("Received : $message"))
    }
}
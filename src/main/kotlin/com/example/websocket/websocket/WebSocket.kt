package com.example.websocket.websocket

import jakarta.websocket.*
import jakarta.websocket.server.ServerEndpoint
import org.springframework.web.socket.WebSocketSession
import java.util.*
import java.util.Collections.synchronizedList

@ServerEndpoint("/ws")
class WebSocket {

    var sessionList = synchronizedList(ArrayList<Session>())
    private lateinit var session: Session
    @OnOpen
    fun onOpen(session: Session){
        this.session = session
        try {
            val basic = session.basicRemote
            basic.sendText("Connected!")
        } catch (e:Exception){
            e.printStackTrace()
        }
        sessionList.add(session)
    }

    @OnMessage
    fun onMessage(session: Session, message: String){
        try {
            val basic = session.basicRemote
            basic.sendText("[WebSocket Message] $message")
        } catch (e:Exception){
            println(e.message)
        }
    }

    @OnMessage
    fun sendMessage(message: String){
        onMessage(this.session, message)
    }

    @OnError
    fun onError(session: Session, throwable: Throwable){
        println("[Error] ${throwable.printStackTrace()}")
    }

    @OnClose
    fun onClose(session: Session){
        sessionList.remove(session)
    }
}
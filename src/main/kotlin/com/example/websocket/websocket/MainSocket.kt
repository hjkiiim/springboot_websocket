package com.example.websocket.websocket

import jakarta.websocket.*
import jakarta.websocket.server.ServerEndpoint
import java.util.*
import java.util.Collections.synchronizedList


@ServerEndpoint("/ws")
class MainSocket {

    var sessionList = synchronizedList(ArrayList<Session>())

    @OnOpen
    fun onOpen(session: Session){
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
        val sender = message
        try {
            val basic = session.basicRemote
            basic.sendText("[Message] $message")
        } catch (e:Exception){
            println(e.message)
        }
    }

    @OnError
    fun onError(session: Session, throwable: Throwable){
    }

    @OnClose
    fun onClose(session: Session){
        sessionList.remove(session)
    }

}
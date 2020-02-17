package com.zhuzichu.android.wan.manager

import com.zhuzichu.android.shared.extension.logi
import com.zhuzichu.android.shared.extension.toast
import okhttp3.*
import okio.ByteString
import java.util.concurrent.TimeUnit


class WebsocketManager {

    private lateinit var websocket: WebSocket

    private val client: OkHttpClient = OkHttpClient.Builder()
        .retryOnConnectionFailure(true)//允许失败重试
        .connectTimeout(15L, TimeUnit.SECONDS)
        .writeTimeout(15L, TimeUnit.SECONDS)
        .readTimeout(15L, TimeUnit.SECONDS)
        .build()

    private val request: Request = Request.Builder().url("ws://121.40.165.18:8800").build()


    private val lisnter = object : WebSocketListener() {
        override fun onOpen(webSocket: WebSocket, response: Response) {
            super.onOpen(webSocket, response)
            "onOpen".logi()
            "连接成功".toast()
        }

        override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
            super.onClosed(webSocket, code, reason)
            "onClosed".logi()
        }

        override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
            super.onClosing(webSocket, code, reason)
            "onClosing".logi()
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            super.onFailure(webSocket, t, response)
            "onFailure".logi()
        }

        override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
            super.onMessage(webSocket, bytes)
            "onMessage1".logi()
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            super.onMessage(webSocket, text)
            "onMessage2:".plus(text).logi()
        }
    }

    fun connect() {
        websocket = client.newWebSocket(request, lisnter)
    }

    fun unConnect() {
        websocket.cancel()
    }

    fun send(text:String){
        websocket.send(text)
    }

}
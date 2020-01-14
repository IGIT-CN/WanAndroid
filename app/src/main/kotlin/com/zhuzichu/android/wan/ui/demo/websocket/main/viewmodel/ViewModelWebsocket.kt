package com.zhuzichu.android.wan.ui.demo.websocket.main.viewmodel

import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.createCommand
import com.zhuzichu.android.wan.manager.WebsocketManager
import javax.inject.Inject

class ViewModelWebsocket @Inject constructor(
    private val websocketManager: WebsocketManager
) : ViewModelAnalyticsBase() {

    val onClickConnect = createCommand {
        websocketManager.connect()
    }

    val onClickUnConnect = createCommand {
        websocketManager.unConnect()
    }

}
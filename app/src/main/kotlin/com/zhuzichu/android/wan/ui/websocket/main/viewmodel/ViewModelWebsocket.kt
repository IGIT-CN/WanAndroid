package com.zhuzichu.android.wan.ui.websocket.main.viewmodel

import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.wan.manager.WebsocketManager
import javax.inject.Inject

class ViewModelWebsocket @Inject constructor(
    private val websocketManager: WebsocketManager
) : ViewModelAnalyticsBase() {

    val onClickConnect = BindingCommand<Any>({
        websocketManager.connect()
    })

    val onClickUnConnect = BindingCommand<Any>({
        websocketManager.unConnect()
    })

}
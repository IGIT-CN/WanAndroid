package com.zhuzichu.android.wan.ui.demo.websocket.main.fragment

import com.zhuzichu.android.shared.base.FragmentAnalyticsBase
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.databinding.FragmentWebsocketBinding
import com.zhuzichu.android.wan.ui.demo.websocket.main.viewmodel.ViewModelWebsocket

class FragmentWebsocket : FragmentAnalyticsBase<FragmentWebsocketBinding, ViewModelWebsocket>() {

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_websocket

}
package com.zhuzichu.android.wan.ui.web.main.viewmodel

import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.mvvm.event.SingleLiveEvent
import com.zhuzichu.android.wan.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.ext.createCommand
import javax.inject.Inject

class ViewModelWeb @Inject constructor() : ViewModelAnalyticsBase() {

    val onExitEvent = SingleLiveEvent<Unit>()

    val title = MutableLiveData<String>()

    val alpha = MutableLiveData(0f)

    val onClickExit = createCommand {
        onExitEvent.call()
    }

}
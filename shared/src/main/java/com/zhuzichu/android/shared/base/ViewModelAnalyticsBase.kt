package com.zhuzichu.android.shared.base

import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.mvvm.event.SingleLiveEvent
import com.zhuzichu.android.shared.extension.createCommand
import com.zhuzichu.android.shared.http.exception.ResponseThrowable

open class ViewModelAnalyticsBase : BaseAutoDisposeViewModel() {

    val onBackCommand = createCommand {
        back()
    }

    internal val handleThrowableEvent: SingleLiveEvent<Payload.PayloadThrowable> = SingleLiveEvent()

    fun handleThrowable(
        throwable: Throwable,
        isToast: Boolean? = null,
        closure: (ResponseThrowable.() -> Unit)? = null
    ) {
        handleThrowableEvent.value = Payload.PayloadThrowable(throwable, isToast, closure)
    }

}
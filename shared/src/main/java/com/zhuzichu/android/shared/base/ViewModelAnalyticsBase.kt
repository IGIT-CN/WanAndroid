package com.zhuzichu.android.shared.base

import com.zhuzichu.android.mvvm.event.SingleLiveEvent
import com.zhuzichu.android.shared.extension.createCommand
import com.zhuzichu.android.shared.http.exception.ResponseThrowable
import com.zhuzichu.android.shared.widget.recycler.LineManager

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


    @JvmOverloads
    fun both(dividerSize: Int = 1): LineManager.Factory = LineManager.both(dividerSize)

    @JvmOverloads
    fun horizontal(dividerSize: Int = 1): LineManager.Factory = LineManager.horizontal(dividerSize)

    @JvmOverloads
    fun vertical(dividerSize: Int = 1): LineManager.Factory = LineManager.vertical(dividerSize)

}
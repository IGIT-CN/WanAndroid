package com.zhuzichu.android.wan.base

import com.zhuzichu.android.mvvm.event.SingleLiveEvent
import com.zhuzichu.android.shared.base.BaseAutoDisposeViewModel
import com.zhuzichu.android.shared.extension.createCommand
import com.zhuzichu.android.shared.http.exception.ResponseThrowable
import com.zhuzichu.android.shared.widget.recycler.LineManager

open class ViewModelAnalyticsBase : BaseAutoDisposeViewModel() {

    val event by lazy { UIChangeLiveData() }

    inner class UIChangeLiveData {
        internal val handleThrowableEvent: SingleLiveEvent<Payload.PayloadThrowable> =
            SingleLiveEvent()
    }

    val onBackCommand = createCommand {
        back()
    }


    fun handleThrowable(
        throwable: Throwable,
        isToast: Boolean? = null,
        closure: (ResponseThrowable.() -> Unit)? = null
    ) {
        event.handleThrowableEvent.value =
            Payload.PayloadThrowable(
                throwable,
                isToast,
                closure
            )
    }

    fun both(): LineManager.Factory = LineManager.both(1)

    fun horizontal(): LineManager.Factory = LineManager.horizontal(1)

    fun vertical(): LineManager.Factory = LineManager.vertical(1)

    @JvmOverloads
    fun gridSpacing(spanCount: Int, spacing: Int = 5): LineManager.Factory =
        LineManager.gridSpacing(spanCount, spacing)
}
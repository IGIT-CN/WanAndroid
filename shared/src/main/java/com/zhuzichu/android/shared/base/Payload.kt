package com.zhuzichu.android.shared.base

import com.zhuzichu.android.shared.http.exception.ResponseThrowable

internal sealed class Payload {

    internal data class PayloadThrowable(
        var throwable: Throwable,
        var isToast: Boolean? = null,
        var closure: (ResponseThrowable.() -> Unit)? = null
    )

}
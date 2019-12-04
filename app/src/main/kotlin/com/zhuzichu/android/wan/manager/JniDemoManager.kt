package com.zhuzichu.android.wan.manager

import androidx.annotation.Keep

@Keep
class JniDemoManager {

    init {
        System.loadLibrary("jnidemo-lib")
    }

    var onInvokeStaticOrDynamicLisenter: (String.() -> Unit)? = null
    /**
     * 调用静态注册的方法
     */
    external fun invokeStaticMethod(): String

    /**
     * 调用动态注册的方法
     */
    external fun invokeDynamicMethod(): String


    /**
     * 调用动态(静态)注册的方法后 在C层调用的方法
     */
    fun onInvokeStaticOrDynamicCallback(text: String) {
        onInvokeStaticOrDynamicLisenter?.invoke(text)
    }

}
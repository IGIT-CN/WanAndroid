package com.zhuzichu.android.wan.manager

import androidx.annotation.Keep

@Keep
class JniDemoManager {

    init {
        System.loadLibrary("jnidemo-lib")
    }

    var onInvokeLisenter: (Int.() -> Unit)? = null
    /**
     * 调用静态注册的方法
     */
    external fun invokeStaticMethod(): String

    /**
     * 调用动态注册的方法
     */
    external fun invokeDynamicMethod(): String

    /**
     * 生成一个随机数
     */
    external fun getRandNumber(): Int

    /**
     *  在C层调用的方法
     */
    fun onInvokeCallback(number: Int) {
        onInvokeLisenter?.invoke(number)
    }

}
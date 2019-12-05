package com.zhuzichu.android.wan.manager

import androidx.annotation.Keep
import com.zhuzichu.android.wan.ui.jni.main.entity.BeanStudent

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
     * 获取一学生对象
     */
    external fun getStudent(): BeanStudent

    /**
     *  在C层调用的方法
     */
    fun onInvokeCallback(number: Int) {
        onInvokeLisenter?.invoke(number)
    }
}
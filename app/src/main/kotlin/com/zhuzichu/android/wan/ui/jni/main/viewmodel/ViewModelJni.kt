package com.zhuzichu.android.wan.ui.jni.main.viewmodel

import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.map
import com.zhuzichu.android.shared.extension.toStringByResId
import com.zhuzichu.android.shared.extension.toast
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.manager.JniDemoManager
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass
import javax.inject.Inject

class ViewModelJni @Inject constructor(
    private val jniDemoManager: JniDemoManager
) : ViewModelAnalyticsBase() {

    val title = MutableLiveData<String>(R.string.jni.toStringByResId())

    init {
        jniDemoManager.onInvokeStaticOrDynamicLisenter = {
            title.value = this
        }
    }

    companion object {
        const val TYPE_STATIC = 0
        const val TYPE_DYNAMIC = 1
    }

    private val closure: Int.() -> Unit = {
        when (this) {
            TYPE_STATIC -> {
                jniDemoManager.invokeStaticMethod().toast()
            }
            TYPE_DYNAMIC -> {
                jniDemoManager.invokeDynamicMethod().toast()
            }
            else -> {
            }
        }
    }

    val items = MutableLiveData<List<Any>>().also {
        it.value = listOf(
            ItemViewModelJni(this, TYPE_STATIC, "静态注册native方法", closure),
            ItemViewModelJni(this, TYPE_DYNAMIC, "动态注册native方法", closure)
        )
    }

    val itemBinding = OnItemBindClass<Any>().apply {
        map<ItemViewModelJni>(BR.item, R.layout.item_jni)
    }

}
package com.zhuzichu.android.wan.ui.me.viewmodel

import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.map
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.ui.ffmpeg.ActivityFFmpeg
import com.zhuzichu.android.wan.ui.jni.ActivityJni
import com.zhuzichu.android.wan.ui.opencv.ActivityOpencv
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass
import javax.inject.Inject

class ViewModelDemo @Inject constructor(
) : ViewModelAnalyticsBase() {

    companion object {
        const val TYPE_JNI = 0
        const val TYPE_OPENCV = 1
        const val TYPE_FFMPEG = 2
    }

    private val closure: Int.() -> Unit = {
        when (this) {
            TYPE_OPENCV -> {
                startActivity(ActivityOpencv::class.java)
            }
            TYPE_JNI -> {
                startActivity(ActivityJni::class.java)
            }
            TYPE_FFMPEG -> {
                startActivity(ActivityFFmpeg::class.java)
            }
            else -> {
            }
        }
    }

    val items = MutableLiveData<List<Any>>().apply {
        value = listOf(
            ItemViewModelDemo(this@ViewModelDemo, TYPE_JNI, R.string.jni, closure),
            ItemViewModelDemo(this@ViewModelDemo, TYPE_OPENCV, R.string.opencv, closure),
            ItemViewModelDemo(this@ViewModelDemo, TYPE_FFMPEG, R.string.ffmpeg, closure)
        )
    }

    val itemBinding = OnItemBindClass<Any>().apply {
        map<ItemViewModelDemo>(BR.item, R.layout.item_demo)
    }
}
package com.zhuzichu.android.wan.ui.me.viewmodel

import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.map
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.ui.demo.camera.ActivityCamera
import com.zhuzichu.android.wan.ui.demo.ffmpeg.ActivityFFmpeg
import com.zhuzichu.android.wan.ui.demo.jni.ActivityJni
import com.zhuzichu.android.wan.ui.demo.main.viewmodel.ItemViewModelDemo
import com.zhuzichu.android.wan.ui.demo.opencv.ActivityOpencv
import com.zhuzichu.android.wan.ui.demo.websocket.ActivityWebsocket
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass
import javax.inject.Inject

class ViewModelDemo @Inject constructor(
) : ViewModelAnalyticsBase() {

    companion object {
        const val TYPE_JNI = 0
        const val TYPE_OPENCV = 1
        const val TYPE_FFMPEG = 2
        const val TYPE_WEBSOCKET = 3
        const val TYPE_CAMERA = 4
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
            TYPE_WEBSOCKET -> {
                startActivity(ActivityWebsocket::class.java)
            }
            TYPE_CAMERA -> {
                startActivity(ActivityCamera::class.java)
            }
            else -> {
            }
        }
    }

    val items = MutableLiveData<List<Any>>().apply {
        value = listOf(
            ItemViewModelDemo(
                this@ViewModelDemo,
                TYPE_JNI,
                R.string.jni,
                closure
            ),
            ItemViewModelDemo(
                this@ViewModelDemo,
                TYPE_OPENCV,
                R.string.opencv,
                closure
            ),
            ItemViewModelDemo(
                this@ViewModelDemo,
                TYPE_FFMPEG,
                R.string.ffmpeg,
                closure
            ),
            ItemViewModelDemo(
                this@ViewModelDemo,
                TYPE_WEBSOCKET,
                R.string.websocket,
                closure
            ),
            ItemViewModelDemo(
                this@ViewModelDemo,
                TYPE_CAMERA,
                R.string.camera,
                closure
            )
        )
    }

    val itemBinding = OnItemBindClass<Any>().apply {
        map<ItemViewModelDemo>(BR.item, R.layout.item_demo)
    }
}
package com.zhuzichu.android.wan.ui.opencv.main.viewmodel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import com.uber.autodispose.autoDispose
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.bindToSchedulers
import com.zhuzichu.android.shared.global.AppGlobal.context
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.extension.logInvokeTime
import com.zhuzichu.android.wan.manager.OpencvManager
import io.reactivex.Flowable
import javax.inject.Inject

class ViewModelErode @Inject constructor(
    private val opencvManager: OpencvManager
) : ViewModelAnalyticsBase() {

    companion object {
        const val TYPE_ERODE = 0
        const val TYPE_DILATE = 1
    }

    private val src = BitmapFactory.decodeResource(context.resources, R.mipmap.guidao)

    var temp: Bitmap? = null

    var morph = MutableLiveData(OpencvManager.MORPH_RECT)

    var type = MutableLiveData(TYPE_ERODE)

    var width = 17

    var height = 17

    val min = 3

    val bitmap = MutableLiveData<Bitmap>().apply {
        value = src
    }

    val onClickRect = BindingCommand<Any>({
        logInvokeTime {
            morph.value = OpencvManager.MORPH_RECT
            updateBitmap()
        }
    })

    val onClickCross = BindingCommand<Any>({
        logInvokeTime {
            morph.value = OpencvManager.MORPH_CROSS
            updateBitmap()
        }
    })

    val onClickEllipse = BindingCommand<Any>({
        logInvokeTime {
            morph.value = OpencvManager.MORPH_ELLIPSE
            updateBitmap()
        }
    })

    val onSeekWidthCommand = BindingCommand<Int>(consumer = {
        this?.let {
            if (it % 3 != 0)
                return@BindingCommand
            width = it + min
            updateBitmap()
        }
    })

    val onSeekHeightCommand = BindingCommand<Int>(consumer = {
        this?.let {
            if (it % 3 != 0)
                return@BindingCommand
            height = it + min
            updateBitmap()
        }
    })

    val onClickErode = BindingCommand<Any>({
        logInvokeTime {
            type.value = TYPE_ERODE
            updateBitmap()
        }
    })

    val onClickDilate = BindingCommand<Any>({
        logInvokeTime {
            type.value = TYPE_DILATE
            updateBitmap()
        }
    })

    val onClickReturn = BindingCommand<Any>({
        bitmap.value = src
    })

    private fun updateBitmap() {
        Flowable.just(src.copy(src.config, true))
            .map {
                when (type.value ?: TYPE_ERODE) {
                    TYPE_ERODE -> getErodeBitmap(it)
                    TYPE_DILATE -> getDilateBitmap(it)
                    else -> getErodeBitmap(it)
                }
            }
            .bindToSchedulers()
            .autoDispose(this)
            .subscribe {
                bitmap.value = it
            }
    }

    private fun getErodeBitmap(src: Bitmap): Bitmap = opencvManager.erode(
        src,
        morph.value ?: OpencvManager.MORPH_RECT,
        width,
        height
    )

    private fun getDilateBitmap(src: Bitmap): Bitmap = opencvManager.dilate(
        src,
        morph.value ?: OpencvManager.MORPH_RECT,
        width,
        height
    )

    override fun onCleared() {
        super.onCleared()
        src.recycle()
    }

}
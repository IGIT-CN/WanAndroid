package com.zhuzichu.android.wan.ui.demo.opencv.main.viewmodel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import com.uber.autodispose.autoDispose
import com.zhuzichu.android.wan.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.ext.bindToSchedulers
import com.zhuzichu.android.shared.ext.createCommand
import com.zhuzichu.android.shared.ext.createTypeCommand
import com.zhuzichu.android.shared.global.AppGlobal.context
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.ext.logInvokeTime
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

    val onClickRect = createCommand {
        logInvokeTime {
            morph.value = OpencvManager.MORPH_RECT
            updateBitmap()
        }
    }

    val onClickCross = createCommand {
        logInvokeTime {
            morph.value = OpencvManager.MORPH_CROSS
            updateBitmap()
        }
    }

    val onClickEllipse = createCommand {
        logInvokeTime {
            morph.value = OpencvManager.MORPH_ELLIPSE
            updateBitmap()
        }
    }

    val onSeekWidthCommand = createTypeCommand<Int> {
        this?.let {
            if (it % 3 != 0)
                return@createTypeCommand
            width = it + min
            updateBitmap()
        }
    }

    val onSeekHeightCommand = createTypeCommand<Int> {
        this?.let {
            if (it % 3 != 0)
                return@createTypeCommand
            height = it + min
            updateBitmap()
        }
    }

    val onClickErode = createCommand {
        logInvokeTime {
            type.value = TYPE_ERODE
            updateBitmap()
        }
    }

    val onClickDilate = createCommand {
        logInvokeTime {
            type.value = TYPE_DILATE
            updateBitmap()
        }
    }

    val onClickReturn = createCommand {
        bitmap.value = src
    }

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
                temp?.recycle()
                bitmap.value = it
                temp = it
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
        temp?.recycle()
    }
}
package com.zhuzichu.android.wan.ui.demo.vxposed.main.fragment

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.PixelFormat
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.Observer
import com.uber.autodispose.autoDispose
import com.zhuzichu.android.wan.base.FragmentAnalyticsBase
import com.zhuzichu.android.shared.bus.RxBus
import com.zhuzichu.android.shared.ext.toast
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.databinding.FragmentVxposedBinding
import com.zhuzichu.android.wan.event.EventClone
import com.zhuzichu.android.wan.ui.demo.vxposed.loading.ActivityVirtualLoading
import com.zhuzichu.android.wan.ui.demo.vxposed.main.viewmodel.ViewModelVxposed

class FragmentVxposed : FragmentAnalyticsBase<FragmentVxposedBinding, ViewModelVxposed>() {

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_vxposed

    override fun initFirstData() {
        super.initFirstData()
        viewModel.loadData()
    }

    override fun initViewObservable() {
        super.initViewObservable()

        viewModel.onClickItemEvent.observe(this, Observer {

            val bitmap = it.icon.value?.let { drawable ->
                drawable.toBitmap(
                    drawable.intrinsicWidth,
                    drawable.intrinsicHeight,
                    if (drawable.opacity != PixelFormat.OPAQUE)
                        Bitmap.Config.ARGB_8888
                    else
                        Bitmap.Config.RGB_565
                )
            } ?: let {
                BitmapFactory.decodeResource(resources, R.drawable.ic_launcher_background)
            }

            val launch = ActivityVirtualLoading.launch(
                requireContext(),
                it.installedAppInfo.packageName,
                it.name.value,
                bitmap,
                0
            )
            if (!launch) {
                "无法打开该App".toast()
            }
        })

        RxBus.toObservable(EventClone.OnSuccessEvent::class.java)
            .autoDispose(viewModel)
            .subscribe(
                {
                    viewModel.loadData()
                },
                {
                    viewModel.handleThrowable(it)
                }
            )
    }

}
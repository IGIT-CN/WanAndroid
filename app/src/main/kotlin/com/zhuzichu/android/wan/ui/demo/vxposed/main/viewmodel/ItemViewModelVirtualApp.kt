package com.zhuzichu.android.wan.ui.demo.vxposed.main.viewmodel

import android.graphics.drawable.Drawable
import androidx.lifecycle.MutableLiveData
import com.lody.virtual.remote.InstalledAppInfo
import com.zhuzichu.android.mvvm.base.BaseViewModel
import com.zhuzichu.android.wan.base.ItemViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.createCommand

class ItemViewModelVirtualApp(
    viewModel: BaseViewModel,
    val installedAppInfo: InstalledAppInfo,
    icon: Drawable,
    name: String
) : ItemViewModelAnalyticsBase(viewModel) {

    val icon = MutableLiveData<Drawable>(icon)

    val name = MutableLiveData<String>(name)

    val onClickApp = createCommand {
        if (viewModel is ViewModelVxposed) {
            viewModel.onClickItemEvent.value = this
        }
    }

}
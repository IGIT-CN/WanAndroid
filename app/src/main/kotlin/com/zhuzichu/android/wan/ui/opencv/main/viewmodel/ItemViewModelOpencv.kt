package com.zhuzichu.android.wan.ui.opencv.main.viewmodel

import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.mvvm.base.BaseViewModel
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.shared.base.ItemViewModelAnalyticsBase
import com.zhuzichu.android.wan.R

class ItemViewModelOpencv(
    viewModel: BaseViewModel,
    type: Int,
    @StringRes stringId: Int
) : ItemViewModelAnalyticsBase(viewModel) {

    val title = MutableLiveData(stringId)

    val onClickItem = BindingCommand<Any>({
        val id = when (type) {
            ViewModelOpencv.TYPE_GRAY -> {
                R.id.action_fragmentOpencv_to_fragmentGray
            }
            else -> {
                R.id.action_fragmentOpencv_to_fragmentGray
            }
        }
        startFragment(id)
    })
}
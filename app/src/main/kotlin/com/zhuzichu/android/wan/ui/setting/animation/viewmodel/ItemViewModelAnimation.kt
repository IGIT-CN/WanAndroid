package com.zhuzichu.android.wan.ui.setting.animation.viewmodel

import com.zhuzichu.android.libs.internal.MainHandler
import com.zhuzichu.android.mvvm.MvvmManager
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.shared.base.ItemViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.createCommand
import com.zhuzichu.android.wan.extension.toAnimationBuild

class ItemViewModelAnimation(
    val viewModel: ViewModelAnimation,
    val type: Int,
    val titleId: Int,
    val isSelected: Boolean
) : ItemViewModelAnalyticsBase(viewModel) {

    val onClickItem = createCommand {
        MvvmManager.animBuilder = type.toAnimationBuild()
        viewModel.globalStorage.animation = type
        MainHandler.postDelayed(50) {
            viewModel.updateData()
        }

    }
}


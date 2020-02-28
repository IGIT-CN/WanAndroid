package com.zhuzichu.android.wan.ui.setting.animation.viewmodel

import com.zhuzichu.android.libs.internal.MainHandler
import com.zhuzichu.android.mvvm.MvvmManager
import com.zhuzichu.android.shared.ext.createCommand
import com.zhuzichu.android.shared.storage.GlobalStorage
import com.zhuzichu.android.wan.base.ItemViewModelAnalyticsBase
import com.zhuzichu.android.wan.ext.toAnimationBuild

class ItemViewModelAnimation(
    val viewModel: ViewModelAnimation,
    val type: Int,
    val titleId: Int,
    val isSelected: Boolean
) : ItemViewModelAnalyticsBase(viewModel) {

    val onClickItem = createCommand {
        MvvmManager.animBuilder = type.toAnimationBuild()
        GlobalStorage.animation = type
        MainHandler.postDelayed(50) {
            viewModel.updateData()
        }

    }
}


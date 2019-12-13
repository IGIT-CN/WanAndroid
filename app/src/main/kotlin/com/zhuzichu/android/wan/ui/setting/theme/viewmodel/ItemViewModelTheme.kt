package com.zhuzichu.android.wan.ui.setting.theme.viewmodel

import com.zhuzichu.android.shared.base.ItemViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.createCommand

data class ItemViewModelTheme(
    val viewModel: ViewModelTheme,
    val titleId: Int,
    val mode: Int,
    val isSelected: Boolean
) : ItemViewModelAnalyticsBase(viewModel) {

    val onClickItem = createCommand {
        viewModel.themeChangeEvent.value = mode
    }
}
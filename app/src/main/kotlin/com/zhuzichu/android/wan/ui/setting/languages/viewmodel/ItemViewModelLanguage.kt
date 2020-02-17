package com.zhuzichu.android.wan.ui.setting.languages.viewmodel

import com.zhuzichu.android.mvvm.base.ItemViewModel
import com.zhuzichu.android.shared.extension.createCommand

data class ItemViewModelLanguage(
    val viewModel: ViewModelLanguages,
    val title: String,
    val locale: String,
    val isSelected: Boolean
) : ItemViewModel(viewModel) {

    val onClickItem = createCommand {
        viewModel.languagesChangeEvent.value = locale
    }
}
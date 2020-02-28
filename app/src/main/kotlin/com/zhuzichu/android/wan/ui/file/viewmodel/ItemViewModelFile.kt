package com.zhuzichu.android.wan.ui.file.viewmodel

import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.shared.ext.createCommand
import com.zhuzichu.android.shared.ext.toast
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.base.ItemViewModelAnalyticsBase
import java.io.File

class ItemViewModelFile(
    viewModel: ViewModelFile,
    val file: File
) : ItemViewModelAnalyticsBase(viewModel) {

    val fileName = MutableLiveData<String>(file.name)

    val fileIcon = MutableLiveData<Int>().apply {
        if (file.isDirectory) {
            value = R.drawable.ic_file_picker_folder
            return@apply
        }

        viewModel.allDefaultFileType.forEach {
            value = if (it.verify(file.name)) {
                it.fileIconResId
            } else {
                R.drawable.ic_file_picker_unknown
            }
        }
    }

    val onClickItem = createCommand {
        if (file.isDirectory) {
            viewModel.loadFileList(file)
        } else {
            "当前不支持文件预览".toast()
        }
    }

}